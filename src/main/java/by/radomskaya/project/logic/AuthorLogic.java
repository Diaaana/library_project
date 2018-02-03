package by.radomskaya.project.logic;

import by.radomskaya.project.dao.AuthorDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public class AuthorLogic {
    private AuthorDAO authorDAO = DAOFactory.getInstance().getAuthorDAO();

    public List<Author> getAuthors() throws DAOException {
        return authorDAO.getAllAuthors();
    }

    public Author getAuthorById(int id) throws DAOException {
        return authorDAO.getAuthorById(id);
    }

    public boolean addAuthor(Author author) throws DAOException {
        return authorDAO.addAuthor(author);
    }

    public boolean deleteAuthor(int id) throws DAOException {
        return authorDAO.deleteAuthor(id);
    }

    public boolean updateAuthor(Author author) throws DAOException {
        return authorDAO.updateAuthor(author);
    }
}
