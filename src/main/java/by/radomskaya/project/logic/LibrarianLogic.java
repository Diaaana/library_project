package by.radomskaya.project.logic;

import by.radomskaya.project.dao.LibrarianDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.exception.LogicException;

import java.util.List;

public class LibrarianLogic {
    private LibrarianDAO librarianDAO = DAOFactory.getInstance().getLibrarianDAO();

    public List<User> getLibrarians() throws LogicException {
        try {
            return librarianDAO.getAllLibrarians();
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public User getLibrarianById(int id) throws LogicException {
        try {
            return librarianDAO.getLibrarianById(id);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public void addLibrarian(User librarian) throws LogicException {
        try {
            librarianDAO.addLibrarian(librarian);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean deleteLibrarian(int id) throws LogicException {
        try {
            return librarianDAO.deleteLibrarian(id);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }

    public boolean updateLibrarian(User librarian) throws LogicException {
        try {
            return librarianDAO.updateLibrarian(librarian);
        } catch (DAOException e) {
            throw new LogicException(e);
        }
    }
}
