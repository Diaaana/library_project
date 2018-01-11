package by.radomskaya.project.dao.impl;

import by.radomskaya.project.dao.GenreDAO;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.pool.ConnectionPool;
import by.radomskaya.project.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GenreDAOImpl implements GenreDAO {
    private final static Logger LOGGER = LogManager.getLogger(GenreDAOImpl.class);
    private final static String INSERT_GENRE = "INSERT INTO genres(name_genre) VALUES(?)";

    @Override
    public boolean addGenre(Book book) throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_GENRE);
            statement.setString(1, book.getGenre());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException("Error add genre" + e);
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
