package by.radomskaya.project.dao;

import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Book;
import org.junit.Test;

public class BookDAOTest {
    private ReaderDAO readerDAO = DAOFactory.getInstance().getReaderDAO();
    private Book book = new Book();

    @Test
    public void testaddBook() {

    }
}
