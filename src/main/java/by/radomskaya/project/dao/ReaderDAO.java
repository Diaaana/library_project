package by.radomskaya.project.dao;

import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.entity.Reader;

import java.util.List;

public interface ReaderDAO {
    List<Reader> getAllReaders() throws DAOException;
    boolean addReader(Reader reader) throws DAOException;
    boolean checkLoginPassword(String login, String password) throws DAOException;
}
