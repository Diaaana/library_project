package by.radomskaya.project.dao.impl;

import by.radomskaya.project.dao.LibrarianDAO;
import by.radomskaya.project.entity.User;
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

public class LibrarianDAOImpl implements LibrarianDAO {
    private final static Logger LOGGER = LogManager.getLogger(LibrarianDAOImpl.class);
    private final static String INSERT_LIBRARIAN = "INSERT INTO users(surname, name, middle_name, login, password, id_role) VALUES(?,?,?,?,?,?);";
    private final static String SELECT_LIBRARIANS = "SELECT number_ticket, surname, name, middle_name, login FROM library.users " +
            "JOIN library.roles ON users.id_role = roles.id_role " +
            "WHERE roles.name_role = 'Библиотекарь';";
    private final static String SELECT_LIBRARIAN_BY_ID = "SELECT number_ticket, surname, name, middle_name, login FROM library.users " +
            "JOIN library.roles ON users.id_role = roles.id_role " +
            "WHERE roles.name_role = 'Библиотекарь' AND number_ticket = ?;";
    private final static String CHECK_LOGIN_PASSWORD = "SELECT login, password FROM library.users JOIN library.roles on users.id_role = roles.id_role WHERE name_role = 'Библиотекарь' AND login = ? AND password = ?";
    private final static String DELETE_LIBRARIAN = "DELETE FROM library.users WHERE number_ticket = ?";
    private final static String UPDATE_LIBRARIAN = "UPDATE library.users SET surname = ?, name = ?, middle_name = ?, login = ? WHERE number_ticket = ?;";
    private final static int ID_ROLE_LIBRARIAN = 2;


    @Override
    public List<User> getAllLibrarians() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ResultSet resultSet;
        User librarian;
        List<User> listLibrarian = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_LIBRARIANS);
            while (resultSet.next()) {
                librarian = getLibrarianFromResultSet(resultSet);
                listLibrarian.add(librarian);
            }
            return listLibrarian;
        } catch (SQLException e) {
            throw new DAOException("Error get all librarians" + e);
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
    public User getLibrarianById(int id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        User librarian = new User();
        try {
            statement = connection.prepareStatement(SELECT_LIBRARIAN_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                librarian = getLibrarianFromResultSet(resultSet);
            }
            return librarian;
        } catch (SQLException e) {
            throw new DAOException("Error get an librarian by id" + e);
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
    public void addLibrarian(User librarian) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_LIBRARIAN);
            statement.setString(1, librarian.getSurname());
            statement.setString(2, librarian.getName());
            statement.setString(3, librarian.getMiddleName());
            statement.setString(4, librarian.getLogin());
            statement.setString(5, librarian.getPassword());
            statement.setInt(6, ID_ROLE_LIBRARIAN);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error add librarian" + e);
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
    public boolean deleteLibrarian(int id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_LIBRARIAN);
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error delete the librarian" + e);
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
    public boolean updateLibrarian(User librarian) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_LIBRARIAN);
            statement.setString(1, librarian.getSurname());
            statement.setString(2, librarian.getName());
            statement.setString(3, librarian.getMiddleName());
            statement.setString(4, librarian.getLogin());
            statement.setInt(5, librarian.getNumberTicket());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error update a librarian" + e);
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
    public boolean checkLoginPassword(String login, String password) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHECK_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Error check librarian by login and password" + e);
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

    private User getLibrarianFromResultSet(ResultSet resultSet) throws SQLException {
        User librarian = new User();
        librarian.setNumberTicket(resultSet.getInt("number_ticket"));
        librarian.setSurname(resultSet.getString("surname"));
        librarian.setName(resultSet.getString("name"));
        librarian.setMiddleName(resultSet.getString("middle_name"));
        librarian.setLogin(resultSet.getString("login"));
        return librarian;
    }
}
