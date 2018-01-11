package by.radomskaya.project.dao.impl;

import by.radomskaya.project.dao.AuthorDAO;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.pool.ConnectionPool;
import by.radomskaya.project.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorDAOImpl implements AuthorDAO {
    private final static Logger LOGGER = LogManager.getLogger(AuthorDAOImpl.class);
    private final static String INSERT_AUTHOR = "INSERT INTO authors(surname, name, middle_name, country) VALUES(?,?,?,?)";

    @Override
    public boolean addAuthor(Author author) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_AUTHOR);
            statement.setString(1, author.getSurname());
            statement.setString(2, author.getName());
            statement.setString(3, author.getMiddleName());
            statement.setString(4, author.getCountryBirth());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error add author" + e);
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
