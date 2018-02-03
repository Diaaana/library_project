package by.radomskaya.project.logic;

import by.radomskaya.project.dao.BookDAO;
import by.radomskaya.project.dao.GenreDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.DAOException;

import java.util.List;
import java.util.Map;

public class BookLogic {
    private BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
    private GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();

    public List<Book> getBooks() throws DAOException {
        return bookDAO.getBooksAndAuthors();
    }

    public Book getBookById(int id) throws DAOException {
        return bookDAO.getBookById(id);
    }

    public List<Book> findBooksByGenre(String genre) throws DAOException {
        return bookDAO.getFoundBooksByGenre(genre);
    }

    public boolean addBook(Book book) throws DAOException {
        return bookDAO.addBook(book);
    }

    public boolean updateBook(Book book) throws DAOException {
        return bookDAO.updateBook(book);
    }

    public boolean deleteBook(int id) throws DAOException {
        return bookDAO.deleteBook(id);
    }

    public List<Book> getFoundBooksByTittle(String tittle) throws DAOException {
        return bookDAO.getFoundBooksByTittle(tittle);
    }

    public List<Book> getFoundBooksByAuthor(String author) throws DAOException {
        return bookDAO.getFoundBooksByAuthor(author);
    }

    public Map<Integer, String> getAllGenres() throws DAOException {
        return genreDAO.getAllGenres();
    }

    public void addBookAndGenre(String[] genres) throws DAOException {
        for (int i = 0; i < genres.length; i++) {
            bookDAO.addBookAndGenre(genres[i]);
        }
    }

    public void updateBookAndGenre(String[] genres, int idBook) throws DAOException {
        for (int i = 0; i < genres.length; i++) {
            bookDAO.updateBookAndGenre(genres[i], idBook);
        }
    }

}
