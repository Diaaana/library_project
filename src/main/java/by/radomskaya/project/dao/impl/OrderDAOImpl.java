package by.radomskaya.project.dao.impl;

import by.radomskaya.project.dao.OrderDAO;
import by.radomskaya.project.entity.Order;
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

public class OrderDAOImpl implements OrderDAO {
    private final static Logger LOGGER = LogManager.getLogger(OrderDAOImpl.class);
    private final static String SELECT_ORDERS = "SELECT * FROM library.orders";
    private final static String INSERT_ORDER = "INSERT INTO library.orders() VALUES()";
    private final static String DELETE_ORDER = "DELETE FROM library.orders WHERE id_order = ?";

    @Override
    public List<Order> getAllOrders() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ORDERS);
            List<Order> listOrders = new ArrayList<>();
            while (resultSet.next()) {
                Order order = new Order();
                listOrders.add(order);
            }
            return listOrders;
        } catch (SQLException e) {
            throw new DAOException("Error get all orders" + e);
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
    public boolean makeOrder(Order order) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_ORDER);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error add order" + e);
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
    public boolean deleteOrder(Order order) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_ORDER);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error delete the order" + e);
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
