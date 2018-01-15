package by.radomskaya.project.dao.impl;

import by.radomskaya.project.dao.BookDAO;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.pool.ConnectionPool;
import by.radomskaya.project.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private final static Logger LOGGER = LogManager.getLogger(BookDAOImpl.class);
    private final static String INSERT_BOOK = "INSERT INTO books(isbn, tittle, date_edition, place_edition, publisher, number_copies, image_book) VALUES(?,?,?,?,?,?,?)";
    private final static String SELECT_ALL = "SELECT isbn, tittle, surname, name, middle_name, name_genre, date_edition, place_edition, publisher, number_copies" +
            " FROM library.books JOIN library.authors ON books.id_book = authors.id_author" +
            " JOIN library.genres ON books.id_book = books.id_book;";
    private final static String SELECT_BOOKS = "SELECT isbn, tittle, name_genre, date_edition, place_edition, publisher, number_copies, image_book FROM library.books JOIN library.book_genre ON books.id_book = book_genre.id_book JOIN library.genres ON book_genre.id_genre = genres.id_genre;";
    private final static String FIND_BOOK = "SELECT tittle FROM library.books WHERE tittle = ?;";
    private final static String SELECT_FIND_BOOK = "SELECT isbn, tittle, name_genre, /*surname, name, middle_name,*/ date_edition, place_edition, publisher, number_copies FROM library.books /*JOIN library.authors ON books.id_book = authors.id_author*/ JOIN library.genres ON books.id_book = genres.id_genre WHERE tittle = ?;";


    @Override
    public List<Book> getAllBooks() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_BOOKS);
            List<Book> listBooks = new ArrayList<>();
            while (resultSet.next()) {
                Book book = new Book();
                book.setIsbn(resultSet.getString("isbn"));
                book.setTittle(resultSet.getString("tittle"));
                book.setGenre(resultSet.getString("name_genre"));
                book.setDateEdition(resultSet.getString("date_edition"));
                book.setPlaceEdition(resultSet.getString("place_edition"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setNumberCopies(resultSet.getInt("number_copies"));
                book.setImage(resultSet.getString("image_book"));
                listBooks.add(book);
            }
            return listBooks;
        } catch (SQLException e) {
            throw new DAOException("Error get all books" + e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Error closing statement", e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error closing connection", e);
            }
        }
    }

    @Override
    public boolean addBook(Book book) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_BOOK);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTittle());
            statement.setString(3, book.getDateEdition());
            statement.setString(4, book.getPlaceEdition());
            statement.setString(5, book.getPublisher());
            statement.setInt(6, book.getNumberCopies());
            statement.setString(7, book.getImage());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error add book" + e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Error closing statement", e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error closing connection", e);
            }
        }
    }

    @Override
    public boolean findBook(String tittle) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_BOOK);
            statement.setString(1, tittle);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Error find a book by tittle" + e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Error closing statement", e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error closing connection", e);
            }
        }
    }

    @Override
    public List<Book> getFoundBook(String tittle) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Book book = new Book();
        List<Book> listBook = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_FIND_BOOK);
            statement.setString(1, tittle);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = getBookFromResultSet(resultSet);
            }
            listBook.add(book);
            return listBook;
        } catch (SQLException e) {
            throw new DAOException("Error get all books" + e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Error closing statement", e);
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Error closing connection", e);
            }
        }
    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setIsbn(resultSet.getString("isbn"));
        book.setTittle(resultSet.getString("tittle"));
        book.setGenre(resultSet.getString("name_genre"));
        book.setDateEdition(resultSet.getString("date_edition"));
        book.setPlaceEdition(resultSet.getString("place_edition"));
        book.setPublisher(resultSet.getString("publisher"));
        book.setNumberCopies(resultSet.getInt("number_copies"));
        return book;
    }
}
