package by.radomskaya.project.dao;

import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.DAOException;

public interface AuthorDAO {
    boolean addAuthor(Author author) throws DAOException;
}
