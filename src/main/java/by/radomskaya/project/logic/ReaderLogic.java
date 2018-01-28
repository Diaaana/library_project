package by.radomskaya.project.logic;

import by.radomskaya.project.dao.ReaderDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public class ReaderLogic {

    public boolean registrationReader(User user) throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.addReader(user);
    }

    public boolean checkReader(String login, String password) throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.checkLoginPasswordUser(login, password);
    }

    public int getNumberTicket(String login, String password) throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.getNumberTicket(login, password);
    }

    public List<User> getReaders() throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.getAllReaders();
    }

    public boolean deleteReader(int numberTicket) throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.deleteReader(numberTicket);
    }

    public User getReaderByTicket(int numberTicket) throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.getUserByTicket(numberTicket);
    }

    public boolean changePassword(int numberTicket, String password) throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.changePassword(numberTicket, password);
    }

    public String getPassword(int numberTicket) throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.getPassword(numberTicket);
    }
}
