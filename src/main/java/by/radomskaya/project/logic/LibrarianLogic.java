package by.radomskaya.project.logic;

import by.radomskaya.project.dao.LibrarianDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public class LibrarianLogic {
    private LibrarianDAO librarianDAO = DAOFactory.getInstance().getLibrarianDAO();

    public boolean checkLibrarian(String login, String password) throws DAOException {
        return librarianDAO.checkLoginPassword(login, password);
    }

    public List<User> getLibrarians() throws DAOException {
        return librarianDAO.getAllLibrarians();
    }

    public User getLibrarianById(int id) throws DAOException {
        return librarianDAO.getLibrarianById(id);
    }

    public void addLibrarian(User librarian) throws DAOException {
        librarianDAO.addLibrarian(librarian);
    }

    public boolean deleteLibrarian(int id) throws DAOException {
        return librarianDAO.deleteLibrarian(id);
    }

    public boolean updateLibrarian(User librarian) throws DAOException {
        return librarianDAO.updateLibrarian(librarian);
    }
}
