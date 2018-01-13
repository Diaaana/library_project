package by.radomskaya.project.dao.impl;

import by.radomskaya.project.dao.LibrarianDAO;
import by.radomskaya.project.entity.Librarian;
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
    private final static Logger LOGGER = LogManager.getLogger(ReaderDAOImpl.class);
    private final static String INSERT_LIBRARIAN = "INSERT INTO librarians(surname, name, middle_name, shift, login, password) VALUES(?,?,?,?,?,?);";
    private final static String SELECT_LIBRARIANS = "SELECT surname, name, middle_name, shift FROM library.librarians;";
    private final static String CHECK_LOGIN_PASSWORD = "SELECT login, password FROM library.librarians JOIN library.roles on librarians.id_role = roles.id_role WHERE name_role = 'Библиотекарь' AND login = ? AND password = ?";

    @Override
    public List<Librarian> getAllLibrarians() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_LIBRARIANS);
            List<Librarian> listLibrarian = new ArrayList<>();
            while (resultSet.next()) {
                Librarian librarian = new Librarian();
                librarian.setSurname(resultSet.getString("surname"));
                librarian.setName(resultSet.getString("name"));
                librarian.setMiddleName(resultSet.getString("middle_name"));
                librarian.setShift(resultSet.getInt("shift"));
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
    public boolean addLibrarian(Librarian librarian) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_LIBRARIAN);
            statement.setString(1, librarian.getSurname());
            statement.setString(2, librarian.getName());
            statement.setString(3, librarian.getMiddleName());
            statement.setInt(4, librarian.getShift());
            statement.setString(5, librarian.getLogin());
            statement.setString(6, librarian.getPassword());
            statement.executeUpdate();
            return true;
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
}
