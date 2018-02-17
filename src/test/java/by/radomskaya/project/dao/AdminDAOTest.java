package by.radomskaya.project.dao;

import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdminDAOTest {
    private static AdminDAO adminDAO;

    @BeforeClass
    public static void init() {
        adminDAO = DAOFactory.getInstance().getAdminDAO();
    }

    @Test
    public void testCheckAdmin() throws DAOException {
        Assert.assertTrue(adminDAO.checkLoginPasswordAdmin("admin", "Aadmin20"));
    }
}
