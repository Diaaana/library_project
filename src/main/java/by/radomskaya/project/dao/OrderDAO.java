package by.radomskaya.project.dao;

import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public interface OrderDAO {
    List<Order> getAllOrders() throws DAOException;
    boolean makeOrder(Order order) throws DAOException;
    boolean deleteOrder(Order order) throws DAOException;
}
