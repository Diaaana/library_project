package by.radomskaya.project.logic;

import by.radomskaya.project.dao.AuthorDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public class AuthorLogic {

    public List<Author> getAuthors() throws DAOException {
        AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
        return authorDAO.getAllAuthors();
    }

    public Author getAuthorById(int id) throws DAOException {
        AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
        return authorDAO.getAuthorById(id);
    }

    public boolean addAuthor(Author author) throws DAOException {
        AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
        return authorDAO.addAuthor(author);
    }

    public boolean deleteAuthor(int id) throws DAOException {
        AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
        return authorDAO.deleteAuthor(id);
    }

    public boolean updateAuthor(Author author) throws DAOException {
        AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();
        return authorDAO.updateAuthor(author);
    }
}
