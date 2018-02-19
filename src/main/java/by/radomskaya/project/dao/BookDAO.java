package by.radomskaya.project.dao;

import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks() throws DAOException;
    boolean addBook(Book book) throws DAOException;
    boolean findBooksByTittle(String tittle) throws DAOException;
    boolean findBooksByAuthor(String author) throws DAOException;
    List<Book> getFoundBooksByTittle(String tittle) throws DAOException;
    List<Book> getFoundBooksByAuthor(String author) throws DAOException;
    boolean deleteBook(Book book) throws DAOException;
}
