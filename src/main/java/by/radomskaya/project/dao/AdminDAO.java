package by.radomskaya.project.dao;

import by.radomskaya.project.exception.DAOException;

public interface AdminDAO {
    boolean checkLoginPasswordAdmin(String login, String password) throws DAOException;
}
