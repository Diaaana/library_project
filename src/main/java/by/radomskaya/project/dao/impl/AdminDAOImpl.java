package by.radomskaya.project.dao.impl;

import by.radomskaya.project.dao.AdminDAO;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.pool.ConnectionPool;
import by.radomskaya.project.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO {
    private final static Logger LOGGER = LogManager.getLogger(AdminDAOImpl.class);
    private final static String INSERT_ADMIN = "INSERT INTO users(surname, name, middle_name, login, password, id_role) VALUES(?,?,?,?,?,?);";
    private final static String CHECK_LOGIN_PASSWORD = "SELECT login, password FROM library.users JOIN library.roles on users.id_role = roles.id_role WHERE name_role = 'Администратор' AND login = ? AND password = ?";
    private final static int ID_ROLE_ADMIN = 1;

    @Override
    public boolean addAdmin(User admin) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_ADMIN);
            statement.setString(1, admin.getSurname());
            statement.setString(2, admin.getName());
            statement.setString(3, admin.getMiddleName());
            statement.setString(4, admin.getLogin());
            statement.setString(5, admin.getPassword());
            statement.setInt(6, ID_ROLE_ADMIN);
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
    public boolean checkLoginPasswordAdmin(String login, String password) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHECK_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Error check admin by login and password" + e);
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
