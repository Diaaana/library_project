package by.radomskaya.project.dao;

import by.radomskaya.project.exception.DAOException;

import java.util.Map;

public interface GenreDAO {
    Map<Integer, String> getAllGenres() throws DAOException;
}
