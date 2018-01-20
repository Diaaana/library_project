package by.radomskaya.project.logic;

import by.radomskaya.project.dao.ReaderDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Reader;
import by.radomskaya.project.exception.DAOException;

public class UserLogic {

    public boolean checkUser(String login, String password) throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.checkLoginPasswordUser(login, password);
    }

    public boolean registrationUser(Reader reader) throws DAOException {
        ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
        return readerDAO.addReader(reader);
    }

}
