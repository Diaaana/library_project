package by.radomskaya.project.dao;

import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.DAOException;

public interface GenreDAO {
    boolean addGenre(Book book) throws DAOException;
}
