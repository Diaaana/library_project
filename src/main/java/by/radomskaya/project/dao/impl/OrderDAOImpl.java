package by.radomskaya.project.dao.impl;

import by.radomskaya.project.dao.OrderDAO;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.entity.Book;
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
    private final static String SELECT_PERSONAL_ORDERS = "SELECT number_ticket, tittle, surname, name, middle_name, image_book " +
            "FROM library.orders " +
            "JOIN library.books ON orders.id_book = books.id_book " +
            "JOIN library.authors ON orders.id_author = authors.id_author " +
            "WHERE number_ticket = ?;";
    private final static String CHECK_PERSONAL_ORDERS = "SELECT number_ticket " +
            "FROM library.orders " +
            "JOIN library.books ON orders.id_book = books.id_book " +
            "JOIN library.authors ON orders.id_author = authors.id_author " +
            "WHERE number_ticket = ?;";
    private final static String INSERT_ORDER = "INSERT INTO library.orders(number_ticket, id_book, id_author) VALUES(?,?,?)";
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
    public boolean checkPersonalOrders(int numberTicket) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHECK_PERSONAL_ORDERS);
            statement.setInt(1, numberTicket);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Error find orders by number ticket" + e);
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
    public List<Order> getPersonalOrders(int numberTicket) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        Order order = new Order();
        List<Order> listOrders = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SELECT_PERSONAL_ORDERS);
            statement.setInt(1, numberTicket);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order = getOrdersFromResultSet(resultSet);
                listOrders.add(order);
            }
            return listOrders;
        } catch (SQLException e) {
            throw new DAOException("Error get personal orders" + e);
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
    public boolean makeOrder(Order order, Book book, Author author) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_ORDER);
            statement.setInt(1, order.getNumberTicket());
            statement.setInt(2, book.getId());
            statement.setInt(3, author.getId());
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

    private Order getOrdersFromResultSet(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        Book book = new Book();
        Author author = new Author();
        order.setNumberTicket(resultSet.getInt("number_ticket"));
        book.setTittle(resultSet.getString("tittle"));
        book.setImage(resultSet.getString("image_book"));
        order.setBook(book);
        author.setSurname(resultSet.getString("surname"));
        author.setName(resultSet.getString("name"));
        author.setMiddleName(resultSet.getString("middle_name"));
        order.setAuthor(author);
        return order;
    }
}
