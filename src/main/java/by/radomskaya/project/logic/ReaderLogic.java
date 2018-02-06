package by.radomskaya.project.logic;

import by.radomskaya.project.dao.ReaderDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public class ReaderLogic {
    private ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();

    public void registrationReader(User user) throws DAOException {
        readerDAO.addReader(user);
    }

    public String checkLoginPassword(String login, String password) throws DAOException {
        return readerDAO.checkLoginPassword(login, password);
    }

    public User getUserByLoginPassword(String login, String password) throws DAOException {
        return readerDAO.getUserByLoginPassword(login, password);
    }

    public int getIdUser(String login, String password) throws DAOException {
        return readerDAO.getIdUser(login, password);
    }

    public int getNumberTicket(String login, String password) throws DAOException {
        return readerDAO.getNumberTicket(login, password);
    }

    public List<User> getReaders() throws DAOException {
        return readerDAO.getAllReaders();
    }

    public boolean deleteReader(int idUser) throws DAOException {
        return readerDAO.deleteReader(idUser);
    }

    public User getReaderByTicket(int numberTicket) throws DAOException {
        return readerDAO.getUserByTicket(numberTicket);
    }

    public boolean changePassword(int numberTicket, String password) throws DAOException {
        return readerDAO.changePassword(numberTicket, password);
    }

    public String getPassword(int numberTicket) throws DAOException {
        return readerDAO.getPassword(numberTicket);
    }

    public void forgotPassword(int numberTicket, String password) throws DAOException {
        readerDAO.forgotPassword(numberTicket, password);
    }

    public boolean updateUser(User user) throws DAOException {
        return readerDAO.updateUser(user);
    }

    public boolean checkLogin(String login) throws DAOException {
        return readerDAO.checkLogin(login);
    }

    public boolean checkMailAndTicket(String mail, int numberTicket) throws DAOException {
        return readerDAO.checkMailAndTicket(mail, numberTicket);
    }
}
