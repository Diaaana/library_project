package by.radomskaya.project.dao;

import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks() throws DAOException;
    boolean addBook(Book book) throws DAOException;
}
