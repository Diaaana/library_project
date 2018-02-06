package by.radomskaya.project.logic;

import by.radomskaya.project.dao.BookDAO;
import by.radomskaya.project.dao.GenreDAO;
import by.radomskaya.project.dao.factory.DAOFactory;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.DAOException;

import java.util.List;
import java.util.Map;

public class BookLogic {
    private final static int QUANTITY_DATA_ON_PAGE = 5;
    private BookDAO bookDAO = DAOFactory.getInstance().getBookDAO();
    private GenreDAO genreDAO = DAOFactory.getInstance().getGenreDAO();

    public List<Book> getBooks() throws DAOException {
        return bookDAO.getBooksAndAuthors();
    }

    public List<Book> getBooksWithPages(int noPage) throws DAOException {
        List<Book> listBooks = getBooks();
        int step = (noPage - 1) * QUANTITY_DATA_ON_PAGE;
        System.out.println(step);
        System.out.println(listBooks.size());
        if (step + QUANTITY_DATA_ON_PAGE >= listBooks.size()) {
            System.out.println(8);
            listBooks = listBooks.subList(step, listBooks.size());
        } else {
            System.out.println(9);
            listBooks = listBooks.subList(step, step + QUANTITY_DATA_ON_PAGE);
        }

        return listBooks;
    }

    public int getNoOfPages() throws DAOException {
        int noOfPages;
        noOfPages = bookDAO.countBooks();
        return (int) Math.ceil(noOfPages * 1.0 / QUANTITY_DATA_ON_PAGE);

    }

    public Book getBookById(int id) throws DAOException {
        return bookDAO.getBookById(id);
    }

    public List<Book> findBooksByGenre(String genre) throws DAOException {
        return bookDAO.getFoundBooksByGenre(genre);
    }

    public boolean addBook(Book book) throws DAOException {
        return bookDAO.addBook(book);
    }

    public boolean updateBook(Book book) throws DAOException {
        return bookDAO.updateBook(book);
    }

    public boolean deleteBook(int id) throws DAOException {
        return bookDAO.deleteBook(id);
    }

    public List<Book> getFoundBooksByTittle(String tittle) throws DAOException {
        return bookDAO.getFoundBooksByTittle(tittle);
    }

    public List<Book> getFoundBooksByAuthor(String author) throws DAOException {
        return bookDAO.getFoundBooksByAuthor(author);
    }

    public Map<Integer, String> getAllGenres() throws DAOException {
        return genreDAO.getAllGenres();
    }

    public void addBookAndGenre(String[] genres) throws DAOException {
        for (int i = 0; i < genres.length; i++) {
            bookDAO.addBookAndGenre(genres[i]);
        }
    }

    public void updateBookAndGenre(String[] genres, int idBook) throws DAOException {
        for (int i = 0; i < genres.length; i++) {
            bookDAO.updateBookAndGenre(genres[i], idBook);
        }
    }



}
