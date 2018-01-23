package by.radomskaya.project.logic;

import by.radomskaya.project.dao.LibrarianDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public class LibrarianLogic {

    public boolean checkLibrarian(String login, String password) throws DAOException {
        LibrarianDAO librarianDAO = DAOFactory.getInstance().getLibrarianDAO();
        return librarianDAO.checkLoginPassword(login, password);
    }

    public List<User> getLibrarians() throws DAOException {
        LibrarianDAO librarianDAO = DAOFactory.getInstance().getLibrarianDAO();
        return librarianDAO.getAllLibrarians();
    }

    public User getLibrarianById(int id) throws DAOException {
        LibrarianDAO librarianDAO = DAOFactory.getInstance().getLibrarianDAO();
        return librarianDAO.getLibrarianById(id);
    }

    public boolean addLibrarian(User librarian) throws DAOException {
        LibrarianDAO librarianDAO = DAOFactory.getInstance().getLibrarianDAO();
        return librarianDAO.addLibrarian(librarian);
    }

    public boolean deleteLibrarian(int id) throws DAOException {
        LibrarianDAO librarianDAO = DAOFactory.getInstance().getLibrarianDAO();
        return librarianDAO.deleteLibrarian(id);
    }

    public boolean updateLibrarian(User librarian) throws DAOException {
        LibrarianDAO librarianDAO = DAOFactory.getInstance().getLibrarianDAO();
        return librarianDAO.updateLibrarian(librarian);
    }
}
