package by.radomskaya.project.dao.impl;

import by.radomskaya.project.dao.ReaderDAO;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.entity.Reader;
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
    private static final Logger LOGGER = LogManager.getLogger(ReaderDAOImpl.class);

    private static final String SELECT_READERS = "SELECT readers.number_ticket, readers.surname, readers.name, readers.middle_name, readers.age, readers.phone_number, readers.mail " +
            "FROM library.readers JOIN library.roles ON roles.id_role = readers.id_role WHERE roles.name_role = 'Пользователь';";
    private static final String INSERT_READER = "INSERT INTO readers(surname, name, middle_name, age, phone_number, mail, login, password) VALUES(?,?,?,?,?,?,?,?)";
    private static final String CHECK_LOGIN_PASSWORD = "SELECT readers.number_ticket FROM library.readers WHERE login = ? AND password = ?;";

    @Override
    public List<Reader> getAllReaders() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_READERS);
            List<Reader> listReaders = new ArrayList<>();
            while (resultSet.next()) {
                Reader reader = new Reader();
                reader.setNumberTicket(resultSet.getInt("number_ticket"));
                reader.setSurname(resultSet.getString("surname"));
                reader.setName(resultSet.getString("name"));
                reader.setMiddleName(resultSet.getString("middle_name"));
                reader.setAge(resultSet.getInt("age"));
                reader.setPhoneNumber(resultSet.getString("phone_number"));
                reader.setMail(resultSet.getString("mail"));
                listReaders.add(reader);
            }
            return listReaders;
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
    public boolean addReader(Reader reader) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_READER);
            statement.setString(1, reader.getSurname());
            statement.setString(2, reader.getName());
            statement.setString(3, reader.getMiddleName());
            statement.setInt(4, reader.getAge());
            statement.setString(5, reader.getPhoneNumber());
            statement.setString(6, reader.getMail());
            statement.setString(7, reader.getLogin());
            statement.setString(8, reader.getPassword());
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error add readers" + e);
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
}
