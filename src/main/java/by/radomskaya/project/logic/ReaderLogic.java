package by.radomskaya.project.logic;

import by.radomskaya.project.dao.ReaderDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Reader;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public class ReaderLogic {

    public boolean checkReader(String login, String password) throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.checkLoginPasswordUser(login, password);
    }

    public int getNumberTicket(String login, String password) throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.getNumberTicket(login, password);
    }

    public List<Reader> getReaders() throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.getAllReaders();
    }

    public boolean deleteReader(int numberTicket) throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.deleteReader(numberTicket);
    }
}