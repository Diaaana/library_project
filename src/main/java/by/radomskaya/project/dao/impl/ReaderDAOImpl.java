package by.radomskaya.project.dao.impl;

import by.radomskaya.project.dao.ReaderDAO;
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


public class ReaderDAOImpl implements ReaderDAO {
    private final static Logger LOGGER = LogManager.getLogger(ReaderDAOImpl.class);
    private final static String SELECT_READERS = "SELECT number_ticket, surname, name, middle_name, age, phone_number, mail, login, image FROM library.users JOIN library.roles ON roles.id_role = users.id_role WHERE roles.name_role = 'Читатель'";
    private final static String INSERT_READER = "INSERT INTO users(surname, name, middle_name, age, phone_number, mail, image, login, password, id_role) VALUES(?,?,?,?,?,?,?,?,?,?)";
    private final static String CHECK_LOGIN_PASSWORD = "SELECT login, password FROM library.users JOIN library.roles on users.id_role = roles.id_role WHERE name_role = 'Читатель' AND login = ? AND password = ?";
    private final static String DELETE_READER = "DELETE FROM library.users WHERE number_ticket = ?";
    private final static String SELECT_NUMBER_TICKET = "SELECT number_ticket FROM library.users WHERE login = ? AND password = ?;";
    private final static String GET_READER_BY_TICKET = "SELECT number_ticket, surname, name, middle_name, age, phone_number, mail, login, password, image FROM library.users WHERE number_ticket = ?";
    private final static String GET_PASSWORD_BY_TICKET = "SELECT password FROM library.users WHERE number_ticket = ?";
    private final static String CHANGE_PASSWORD = "UPDATE library.users SET password = ? WHERE number_ticket = ?;";
    private final static int ID_ROLE_READER = 3;


    @Override
    public List<User> getAllReaders() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_READERS);
            List<User> listUsers = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setNumberTicket(resultSet.getInt("number_ticket"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setMiddleName(resultSet.getString("middle_name"));
                user.setAge(resultSet.getInt("age"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setMail(resultSet.getString("mail"));
                user.setLogin(resultSet.getString("login"));
                user.setProfilePhoto(resultSet.getString("image"));
                user.setLogin(resultSet.getString("login"));
                listUsers.add(user);
            }
            return listUsers;
        } catch (SQLException e) {
            throw new DAOException("Error get all readers" + e);
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
    public int getNumberTicket(String login, String password) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        int numberTicket = 0;
        try {
            statement = connection.prepareStatement(SELECT_NUMBER_TICKET);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                numberTicket = resultSet.getInt("number_ticket");
            }
        } catch (SQLException e) {
            throw new DAOException("Error get all readers" + e);
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
        return numberTicket;
    }

    @Override
    public boolean addReader(User user) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_READER);
            statement.setString(1, user.getSurname());
            statement.setString(2, user.getName());
            statement.setString(3, user.getMiddleName());
            statement.setInt(4, user.getAge());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getMail());
            statement.setString(7, user.getProfilePhoto());
            statement.setString(8, user.getLogin());
            statement.setString(9, user.getPassword());
            statement.setInt(10, ID_ROLE_READER);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error add user" + e);
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
    public boolean deleteReader(int number_ticket) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_READER);
            statement.setInt(1, number_ticket);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error delete a reader" + e);
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
    public boolean checkLoginPasswordUser(String login, String password) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            statement = connection.prepareStatement(CHECK_LOGIN_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new DAOException("Error check reader by login and password" + e);
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
    public User getUserByTicket(int numberTicket) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        User user = new User();
        try {
            statement = connection.prepareStatement(GET_READER_BY_TICKET);
            statement.setInt(1, numberTicket);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user.setNumberTicket(resultSet.getInt("number_ticket"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));
                user.setMiddleName(resultSet.getString("middle_name"));
                user.setAge(resultSet.getInt("age"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setMail(resultSet.getString("mail"));
                user.setLogin(resultSet.getString("login"));
                user.setProfilePhoto(resultSet.getString("image"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            throw new DAOException("Error get an user by number ticket" + e);
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
    public boolean changePassword(int numberTicket, String password) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHANGE_PASSWORD);
            statement.setString(1, password);
            statement.setInt(2, numberTicket);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error get an user by number ticket" + e);
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
    public String getPassword(int numberTicket) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
        String password = new String();
        try {
            statement = connection.prepareStatement(GET_PASSWORD_BY_TICKET);
            statement.setInt(1, numberTicket);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString("password");
            }
            return password;
        } catch (SQLException e) {
            throw new DAOException("Error get an user by number ticket" + e);
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
