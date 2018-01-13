package by.radomskaya.project.logic;

import by.radomskaya.project.dao.LibrarianDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.exception.DAOException;

public class LibrarianLogic {

    public boolean checkLibrarian(String login, String password) throws DAOException {
        LibrarianDAO librarianDAO = DAOFactory.getInstance().getLibrarianDAO();
        return librarianDAO.checkLoginPassword(login, password);
    }
}
