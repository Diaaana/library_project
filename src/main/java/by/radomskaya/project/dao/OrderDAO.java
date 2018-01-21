package by.radomskaya.project.dao;

import by.radomskaya.project.entity.Author;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public interface OrderDAO {
    List<Order> getAllOrders() throws DAOException;
    boolean checkPersonalOrders(int numberTicket) throws DAOException;
    List<Order> getPersonalOrders(int numberTicket) throws DAOException;
    boolean makeOrder(Order order, Book book, Author author) throws DAOException;
    boolean deleteOrder(Order order) throws DAOException;
}
