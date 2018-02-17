package by.radomskaya.project.dao;

import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class OrderDAOTest {
    private static OrderDAO orderDAO;

    @BeforeClass
    public static void init() {
        orderDAO = DAOFactory.getInstance().getOrderDAO();
    }

    @Test
    public void testGetAllOrders() throws DAOException {
        List<Order> orders = orderDAO.getAllOrders();
        Assert.assertFalse(orders.isEmpty());
    }

    @Test
    public void testCheckPersonalOrders() throws DAOException {
        Assert.assertTrue(orderDAO.checkPersonalOrders(1));
    }

    @Test
    public void testGetPersonalOrders() throws DAOException {
        List<Order> orders = orderDAO.getPersonalOrders(1);
        Assert.assertFalse(orders.isEmpty());
    }

    @Test
    public void testDeleteOrder() throws DAOException {
        Assert.assertTrue(orderDAO.makeOrder(2,2,2));
    }
}
