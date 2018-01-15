package by.radomskaya.project.logic;

import by.radomskaya.project.dao.BookDAO;
import by.radomskaya.project.dao.ReaderDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.entity.Reader;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public class UserLogic {

    public boolean checkUser(String login, String password) throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.checkLoginPasswordUser(login, password);
    }

    public boolean registrationUser(Reader reader) throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.addReader(reader);
    }

    public List<Book> getBooks() throws DAOException {
        BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
        return bookDAO.getAllBooks();
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
