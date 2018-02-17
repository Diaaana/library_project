package by.radomskaya.project.dao;

import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.DAOException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class AuthorDAOTest {
    private static AuthorDAO authorDAO;

    @BeforeClass
    public static void init() {
        authorDAO = DAOFactory.getInstance().getAuthorDAO();
    }

    @Test
    public void testGetAllAuthors() throws DAOException {
        List<Author> authors = authorDAO.getAllAuthors();
        Assert.assertFalse(authors.isEmpty());
    }
}
