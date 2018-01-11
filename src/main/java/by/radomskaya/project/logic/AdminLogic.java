package by.radomskaya.project.logic;

import by.radomskaya.project.dao.AuthorDAO;
import by.radomskaya.project.dao.BookDAO;
import by.radomskaya.project.dao.GenreDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public class AdminLogic {

    public boolean addBook(Book book) throws DAOException {
        BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
        return bookDAO.addBook(book);
    }

    public boolean addAuthor(Author author) throws DAOException {
        AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
        return authorDAO.addAuthor(author);
    }

    public boolean addGenre(Book book) throws DAOException {
        GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();
        return genreDAO.addGenre(book);
    }

    public List<Book> getBooks() throws DAOException {
        BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
        return bookDAO.getAllBooks();
    }

}
