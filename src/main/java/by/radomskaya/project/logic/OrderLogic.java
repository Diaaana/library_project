package by.radomskaya.project.logic;

import by.radomskaya.project.dao.OrderDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public class OrderLogic {

    public boolean addToCart(Order order, Book book, Author author) throws DAOException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        return orderDAO.makeOrder(order, book, author);
    }

    public boolean checkPersonalOrders(int numberTicket) throws DAOException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        return orderDAO.checkPersonalOrders(numberTicket);
    }

    public List<Order> getPersonalOrders(int numberTicket) throws DAOException {
        OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
        return orderDAO.getPersonalOrders(numberTicket);
    }
}
