package by.radomskaya.project.dao.impl;

import by.radomskaya.project.dao.GenreDAO;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.pool.ConnectionPool;
import by.radomskaya.project.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class GenreDAOImpl implements GenreDAO {
    private final static Logger LOGGER = LogManager.getLogger(GenreDAOImpl.class);
    private final static String SELECT_ALL_GENRES = "SELECT id_genre, name_genre FROM library.genres;";

    @Override
    public Map<Integer, String> getAllGenres() throws DAOException {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        Statement statement = null;
        ResultSet resultSet;
        Map<Integer, String> mapGenres = new HashMap<>();
        int idGenre;
        String nameGenre;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_GENRES);
            while (resultSet.next()) {
                idGenre = resultSet.getInt("id_genre");
                nameGenre = resultSet.getString("name_genre");
                mapGenres.put(idGenre, nameGenre);
            }
            return mapGenres;
        } catch (SQLException e) {
            throw new DAOException("Error get all genres" + e);
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
