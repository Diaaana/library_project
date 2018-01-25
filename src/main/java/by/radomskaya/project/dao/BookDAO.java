package by.radomskaya.project.dao;

import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public interface BookDAO {
    List<Book> getBooksAndAuthors() throws DAOException;
    Book getBookById(int id) throws DAOException;
    boolean addBook(Book book) throws DAOException;
    void addBookAndGenre(int idGenre) throws DAOException;
    boolean deleteBook(int id) throws DAOException;
    boolean updateBook(Book book) throws DAOException;
    boolean findBooksByTittle(String tittle) throws DAOException;
    List<Book> getFoundBooksByTittle(String tittle) throws DAOException;
    boolean findBooksByAuthor(String author) throws DAOException;
    List<Book> getFoundBooksByAuthor(String author) throws DAOException;
}
