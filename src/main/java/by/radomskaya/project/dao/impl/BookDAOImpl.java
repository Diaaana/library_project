package by.radomskaya.project.dao.impl;

import by.radomskaya.project.dao.BookDAO;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.pool.ConnectionPool;
import by.radomskaya.project.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private final static Logger LOGGER = LogManager.getLogger(BookDAOImpl.class);
    private final static String INSERT_BOOK = "INSERT INTO books(isbn, tittle, date_edition, place_edition, publisher, number_copies) VALUES(?,?,?,?,?,?)";

    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public boolean addBook(Book book) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_BOOK);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getTittle());
            statement.setString(3, book.getDataEdition());
            statement.setString(4, book.getPlaceEdition());
            statement.setString(5, book.getPublisher());
            statement.setInt(6, book.getNumberCopies());
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
}
