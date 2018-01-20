package by.radomskaya.project.dao;

import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public interface AuthorDAO {
    List<Author> getAllAuthors() throws DAOException;
    boolean addAuthor(Author author) throws DAOException;
    boolean deleteAuthor(int id) throws  DAOException;
}
