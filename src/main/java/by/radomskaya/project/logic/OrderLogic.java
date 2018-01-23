package by.radomskaya.project.logic;

import by.radomskaya.project.dao.BorrowBookDAO;
import by.radomskaya.project.dao.OrderDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public class OrderLogic {

    public List<Order> getAllOrders() throws DAOException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        return orderDAO.getAllOrders();
    }

    public boolean addToCart(Order order, Book book, Author author) throws DAOException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        return orderDAO.makeOrder(order, book, author);
    }

    public void deleteOrderById(int id) throws DAOException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        orderDAO.deleteOrderById(id);
    }

    public void deleteOrderByNumberTicket(int numberTicket) throws DAOException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        orderDAO.deleteOrderByNumberTicket(numberTicket);
    }

    public boolean checkPersonalOrders(int numberTicket) throws DAOException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        return orderDAO.checkPersonalOrders(numberTicket);
    }

    public List<Order> getPersonalOrders(int numberTicket) throws DAOException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        return orderDAO.getPersonalOrders(numberTicket);
    }

    public void takeOrder(Order order) throws DAOException {
        BorrowBookDAO borrowBookDAO = DAOFactory.getInstance().getBorrowBookDAO();
        borrowBookDAO.addOrder(order);
    }
}
