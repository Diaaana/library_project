package by.radomskaya.project.dao;

import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.DAOException;

public interface BorrowBookDAO {
    void addOrder(Order order) throws DAOException;
}
