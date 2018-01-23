package by.radomskaya.project.dao;

import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public interface ReaderDAO {
    List<User> getAllReaders() throws DAOException;
    int getNumberTicket(String login, String password) throws DAOException;
    boolean addReader(User user) throws DAOException;
    boolean deleteReader(int numberTicket) throws DAOException;
    boolean checkLoginPasswordUser(String login, String password) throws DAOException;
}
