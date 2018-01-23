package by.radomskaya.project.dao.impl;

import by.radomskaya.project.dao.BookDAO;
import by.radomskaya.project.entity.Author;
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
    private final static String SELECT_BOOKS_AND_AUTHORS = "SELECT books.id_book, isbn, tittle, authors.id_author, surname, name, middle_name, name_genre, date_edition, place_edition, publisher, number_copies, image_book " +
            "FROM library.books JOIN library.book_genre ON books.id_book = book_genre.id_book " +
            "JOIN library.genres ON book_genre.id_genre = genres.id_genre " +
            "JOIN library.book_author ON books.id_book = book_author.id_book " +
            "JOIN library.authors ON book_author.id_author = authors.id_author;";
    private final static String SELECT_BOOKS = "SELECT books.id_book, isbn, tittle, name_genre, date_edition, place_edition, publisher, number_copies, image_book " +
            "FROM library.books JOIN library.book_genre ON books.id_book = book_genre.id_book " +
            "JOIN library.genres ON book_genre.id_genre = genres.id_genre;";
    private final static String SELECT_BOOK_BY_ID = "SELECT books.id_book, isbn, tittle, authors.id_author, surname, name, middle_name, name_genre, date_edition, place_edition, publisher, number_copies, image_book " +
            "FROM library.books JOIN library.book_genre ON books.id_book = book_genre.id_book " +
            "JOIN library.genres ON book_genre.id_genre = genres.id_genre " +
            "JOIN library.book_author ON books.id_book = book_author.id_book " +
            "JOIN library.authors ON book_author.id_author = authors.id_author " +
            "WHERE books.id_book = ?;";
    private final static String FIND_BOOKS_BY_TITTLE = "SELECT tittle FROM library.books WHERE tittle = ?;";
    private final static String SELECT_FIND_BOOKS_BY_TITTLE = "SELECT books.id_book, authors.id_author, isbn, tittle, surname, name, middle_name, name_genre, date_edition, place_edition, publisher, number_copies, image_book " +
            "FROM library.books JOIN library.book_genre ON book_genre.id_book = books.id_book " +
            "JOIN library.genres ON book_genre.id_genre = genres.id_genre " +
            "JOIN library.book_author ON books.id_book = book_author.id_book " +
            "JOIN library.authors ON authors.id_author = book_author.id_author " +
            "WHERE tittle = ?;";
    private final static String FIND_BOOKS_BY_AUTHOR = "SELECT surname FROM library.books JOIN library.book_author ON books.id_book = book_author.id_book JOIN library.authors ON book_author.id_author = authors.id_author WHERE surname = ?;";
    private final static String SELECT_FIND_BOOKS_BY_AUTHOR = "SELECT books.id_book, authors.id_author, isbn, tittle, surname, name, middle_name, name_genre, date_edition, place_edition, publisher, number_copies, image_book " +
            "FROM library.books JOIN library.book_genre ON book_genre.id_book = books.id_book " +
            "JOIN library.genres ON book_genre.id_genre = genres.id_genre " +
            "JOIN library.book_author ON books.id_book = book_author.id_book " +
            "JOIN library.authors ON authors.id_author = book_author.id_author " +
            "WHERE surname = ?;";
    private final static String DELETE_BOOK = "DELETE FROM library.books WHERE id_book = ?";
    private final static String UPDATE_BOOK_BY_ID = "UPDATE library.books " +
            "JOIN library.book_author ON books.id_book = book_author.id_book " +
            "JOIN library.authors ON book_author.id_author = authors.id_author " +
            "JOIN library.book_genre ON books.id_book = book_genre.id_book " +
            "JOIN library.genres ON book_genre.id_genre = genres.id_genre " +
            "SET isbn = ?, tittle = ?, surname = ?, name = ?, middle_name = ?, name_genre = ?, date_edition = ?, place_edition = ?, publisher = ?, number_copies = ? " +
            "WHERE books.id_book = ?;";

    @Override
    public List<Book> getBooksAndAuthors() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ResultSet resultSet;
        Book book;
        List<Book> listBooks = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_BOOKS_AND_AUTHORS);
            while (resultSet.next()) {
                book = getBooksFromResultSet(resultSet);
                listBooks.add(book);
            }
            return listBooks;
        } catch (SQLException e) {
            throw new DAOException("Error get all book" + e);
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
    public Book getBookById(int id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        Book book = new Book();
        try {
            statement = connection.prepareStatement(SELECT_BOOK_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                book = getBooksFromResultSet(resultSet);
            }
            return book;
        } catch (SQLException e) {
            throw new DAOException("Error get found book by id" + e);
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
    public boolean findBooksByTittle(String tittle) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_BOOKS_BY_TITTLE);
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
    public boolean findBooksByAuthor(String author) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FIND_BOOKS_BY_AUTHOR);
            statement.setString(1, author);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Error find a book by author" + e);
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
    public List<Book> getFoundBooksByTittle(String tittle) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Book book = new Book();
        List<Book> listBook = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_FIND_BOOKS_BY_TITTLE);
            statement.setString(1, tittle);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                book = getBooksFromResultSet(resultSet);
                listBook.add(book);
            }
            return listBook;
        } catch (SQLException e) {
            throw new DAOException("Error get found book by tittle" + e);
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
    public List<Book> getFoundBooksByAuthor(String author) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Book book;
        List<Book> listBook = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_FIND_BOOKS_BY_AUTHOR);
            statement.setString(1, author);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                book = getBooksFromResultSet(resultSet);
                listBook.add(book);
            }
            return listBook;
        } catch (SQLException e) {
            throw new DAOException("Error get found book by author" + e);
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
    public boolean deleteBook(int id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_BOOK);
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error delete the book" + e);
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
    public boolean updateBook(Book book) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_BOOK_BY_ID);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTittle());
            statement.setString(3, book.getAuthor().getSurname());
            statement.setString(4, book.getAuthor().getName());
            statement.setString(5, book.getAuthor().getMiddleName());
            statement.setString(6, book.getGenre());
            statement.setString(7, book.getDateEdition());
            statement.setString(8, book.getPlaceEdition());
            statement.setString(9, book.getPublisher());
            statement.setInt(10, book.getNumberCopies());
            //statement.setString(10, book.getImage());
            statement.setInt(11, book.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error update book" + e);
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

    private Book getBooksFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        Author author = new Author();
        book.setId(resultSet.getInt("id_book"));
        book.setIsbn(resultSet.getString("isbn"));
        book.setTittle(resultSet.getString("tittle"));
        author.setId(resultSet.getInt("id_author"));
        author.setSurname(resultSet.getString("surname"));
        author.setName(resultSet.getString("name"));
        author.setMiddleName(resultSet.getString("middle_name"));
        book.setAuthor(author);
        book.setGenre(resultSet.getString("name_genre"));
        book.setDateEdition(resultSet.getString("date_edition"));
        book.setPlaceEdition(resultSet.getString("place_edition"));
        book.setPublisher(resultSet.getString("publisher"));
        book.setNumberCopies(resultSet.getInt("number_copies"));
        book.setImage(resultSet.getString("image_book"));
        return book;
    }
}
