package by.radomskaya.project.dao.impl;

import by.radomskaya.project.dao.BorrowBookDAO;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.pool.ConnectionPool;
import by.radomskaya.project.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorrowBookDAOImpl implements BorrowBookDAO {
    private final static Logger LOGGER = LogManager.getLogger(BorrowBookDAOImpl.class);
    private final static String INSERT_BORROWED_BOOK = "INSERT INTO library.borrow_book(number_ticket, id_book, date_borrow, date_return, method_borrow) VALUES(?,?,?,?,?);";

    @Override
    public void addOrder(Order order) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_BORROWED_BOOK);
            statement.setInt(1, order.getNumberTicket());
            statement.setInt(2, order.getBook().getId());
            statement.setString(3, order.getDateBorrow());
            statement.setString(4, order.getDateReturn());
            statement.setString(5, order.getMethodBorrow());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error add borrowed book" + e);
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
