package by.radomskaya.project.logic;

import by.radomskaya.project.dao.BookDAO;
import by.radomskaya.project.dao.GenreDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public class BookLogic {

    public List<Book> getBooks() throws DAOException {
        BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
        return bookDAO.getBooksAndAuthors();
    }

    public Book getBookById(int id) throws DAOException {
        BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
        return bookDAO.getBookById(id);
    }

    public boolean addBook(Book book) throws DAOException {
        BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
        return bookDAO.addBook(book);
    }

    public boolean updateBook(Book book) throws DAOException {
        BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
        return bookDAO.updateBook(book);
    }

    public boolean addGenre(Book book) throws DAOException {
        GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
        return genreDAO.addGenre(book);
    }

    public boolean deleteBook(int id) throws DAOException {
        BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
        return bookDAO.deleteBook(id);
    }

    public boolean findBooksByTittle(String tittle) throws DAOException {
        BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
        return bookDAO.findBooksByTittle(tittle);
    }

    public boolean findBooksByAuthor(String author) throws DAOException {
        BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
        return bookDAO.findBooksByAuthor(author);
    }

    public List<Book> getFoundBooksByTittle(String tittle) throws DAOException {
        BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
        return bookDAO.getFoundBooksByTittle(tittle);
    }

    public List<Book> getFoundBooksByAuthor(String author) throws DAOException {
        BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
        return bookDAO.getFoundBooksByAuthor(author);
    }

}
