package by.radomskaya.project.logic;

import by.radomskaya.project.dao.BorrowBookDAO;
import by.radomskaya.project.dao.OrderDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public class OrderLogic {
    private OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();
    private BorrowBookDAO borrowBookDAO = DAOFactory.getInstance().getBorrowBookDAO();

    public List<Order> getAllOrders() throws DAOException {
        return orderDAO.getAllOrders();
    }

    public boolean addToCart(int idUser, int idBook, int idAuthor) throws DAOException {
        return orderDAO.makeOrder(idUser, idBook, idAuthor);
    }

    public void deleteOrderById(int id) throws DAOException {
        orderDAO.deleteOrderById(id);
    }

    public void deleteOrderByNumberTicket(int numberTicket) throws DAOException {
        orderDAO.deleteOrderByNumberTicket(numberTicket);
    }

    public boolean checkPersonalOrders(int idUser) throws DAOException {
        return orderDAO.checkPersonalOrders(idUser);
    }

    public List<Order> getPersonalOrders(int idUser) throws DAOException {
        return orderDAO.getPersonalOrders(idUser);
    }

    public void takeOrder(Order order) throws DAOException {
        borrowBookDAO.addOrder(order);
    }

    public List<Order> getApprovedOrders(int idUser) throws DAOException {
        return borrowBookDAO.getApprovedOrders(idUser);
    }
}
