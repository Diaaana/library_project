package by.radomskaya.project.dao;

import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.DAOException;

public interface AdminDAO {
    boolean addAdmin(User admin) throws DAOException;
    boolean checkLoginPasswordAdmin(String login, String password) throws DAOException;
}
