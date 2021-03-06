package by.radomskaya.project.dao;

import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.DAOException;

import java.util.List;
import java.util.Map;

public interface BookDAO {
    List<Book> getBooksAndAuthors() throws DAOException;
    Book getBookById(int id) throws DAOException;
    Map<Integer, String> getAllGenres() throws DAOException;
    boolean addBook(Book book) throws DAOException;
    void addBookAndGenre(String genre) throws DAOException;
    void updateBookAndGenre(String genre, int idBook) throws DAOException;
    boolean deleteBook(int id) throws DAOException;
    boolean updateBook(Book book) throws DAOException;
    List<Book> getFoundBooksByGenre(String genre) throws DAOException;
    List<Book> getFoundBooksByTittle(String tittle) throws DAOException;
    List<Book> getFoundBooksByAuthor(String author) throws DAOException;
    int countBooks() throws DAOException;
}
