package by.radomskaya.project.dao;

import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public interface LibrarianDAO {
    List<User> getAllLibrarians() throws DAOException;
    User getLibrarianById(int id) throws DAOException;
    void addLibrarian(User librarian) throws DAOException;
    boolean deleteLibrarian(int id) throws DAOException;
    boolean updateLibrarian(User librarian) throws DAOException;
    boolean checkLoginPassword(String login, String password) throws DAOException;
}
