package by.radomskaya.project.dao;

import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.DAOException;

import java.util.List;

public interface BorrowBookDAO {
    void addOrder(Order order) throws DAOException;
    List<Order> getApprovedOrders(int idUser) throws DAOException;
}
