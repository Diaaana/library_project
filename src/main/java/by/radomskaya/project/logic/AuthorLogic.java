package by.radomskaya.project.logic;

import by.radomskaya.project.dao.AuthorDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.exception.LogicException;

import java.util.List;

public class AuthorLogic {
    private AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();

    public List<Author> getAuthors() throws LogicException {
        try {
            return authorDAO.getAllAuthors();
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public Author getAuthorById(int id) throws LogicException {
        try {
            return authorDAO.getAuthorById(id);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean addAuthor(Author author) throws LogicException {
        try {
            return authorDAO.addAuthor(author);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean deleteAuthor(int id) throws LogicException {
        try {
            return authorDAO.deleteAuthor(id);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean updateAuthor(Author author) throws LogicException {
        try {
            return authorDAO.updateAuthor(author);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }
}
