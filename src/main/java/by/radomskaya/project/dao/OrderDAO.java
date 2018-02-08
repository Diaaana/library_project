package by.radomskaya.project.dao;

import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public interface OrderDAO {
    List<Order> getAllOrders() throws DAOException;
    boolean checkPersonalOrders(int idUser) throws DAOException;
    List<Order> getPersonalOrders(int idUser) throws DAOException;
    boolean makeOrder(int idUser, int idBook, int idAuthor) throws DAOException;
    void deleteOrderById(int id) throws DAOException;
}
