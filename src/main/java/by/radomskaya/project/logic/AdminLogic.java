package by.radomskaya.project.logic;

import by.radomskaya.project.dao.*;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.entity.Librarian;
import by.radomskaya.project.entity.Reader;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public class AdminLogic {

    public boolean checkAdmin(String login, String password) throws DAOException {
        AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();
        return adminDAO.checkLoginPasswordAdmin(login, password);
    }

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

    public boolean addLibrarian(Librarian librarian) throws DAOException {
        LibrarianDAO librarianDAO = DAOFactory.getInstance().getLibrarianDAO();
        return librarianDAO.addLibrarian(librarian);
    }

    public List<Reader> getReaders() throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.getAllReaders();
    }

    public List<Book> getBooks() throws DAOException {
        BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
        return bookDAO.getAllBooks();
    }

    public List<Librarian> getLibrarians() throws DAOException {
        LibrarianDAO librarianDAO = DAOFactory.getInstance().getLibrarianDAO();
        return librarianDAO.getAllLibrarians();
    }

}
