package by.radomskaya.project.dao;

import by.radomskaya.project.entity.Librarian;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public interface LibrarianDAO {
    List<Librarian> getAllLibrarians() throws DAOException;
    boolean addLibrarian(Librarian librarian) throws DAOException;
    boolean deleteLibrarian(Librarian librarian) throws DAOException;
    boolean checkLoginPassword(String login, String password) throws DAOException;
}
