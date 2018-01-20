package by.radomskaya.project.dao.impl;

import by.radomskaya.project.dao.AuthorDAO;
import by.radomskaya.project.entity.Author;
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

public class AuthorDAOImpl implements AuthorDAO {
    private final static Logger LOGGER = LogManager.getLogger(AuthorDAOImpl.class);
    private final static String SELECT_AUTHORS = "SELECT id_author, surname, name, middle_name, country FROM library.authors;";
    private final static String INSERT_AUTHOR = "INSERT INTO authors(surname, name, middle_name, country) VALUES(?,?,?,?)";
    private final static String DELETE_AUTHOR = "DELETE FROM library.authors WHERE id_author = ?";

    @Override
    public List<Author> getAllAuthors() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_AUTHORS);
            List<Author> listAuthors = new ArrayList<>();
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt("id_author"));
                author.setSurname(resultSet.getString("surname"));
                author.setName(resultSet.getString("name"));
                author.setMiddleName(resultSet.getString("middle_name"));
                author.setCountryBirth(resultSet.getString("country"));
                listAuthors.add(author);
            }
            return listAuthors;
        } catch (SQLException e) {
            throw new DAOException("Error get all authors" + e);
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

    @Override
    public boolean deleteAuthor(int id) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_AUTHOR);
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error delete an author" + e);
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
