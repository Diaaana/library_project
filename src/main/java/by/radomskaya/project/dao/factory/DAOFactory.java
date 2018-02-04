package by.radomskaya.project.dao.factory;

import by.radomskaya.project.dao.*;
import by.radomskaya.project.dao.impl.*;

public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();

    private final AdminDAO adminDAO = new AdminDAOImpl();
    private final AuthorDAO authorDAO = new AuthorDAOImpl();
    private final BookDAO bookDAO = new BookDAOImpl();
    private final BorrowBookDAO borrowBookDAO = new BorrowBookDAOImpl();
    private final ReaderDAO readerDAO = new ReaderDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();

    public DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    public AdminDAO getAdminDAO() { return adminDAO; }

    public AuthorDAO getAuthorDAO() {
        return authorDAO;
    }

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public BorrowBookDAO getBorrowBookDAO() {
        return borrowBookDAO;
    }

    public ReaderDAO getReaderDAO() {
        return readerDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }
}
