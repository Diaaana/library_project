package by.radomskaya.project.command.admin.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.ADMIN_BOOKS_PAGE;

public class UpdateBookCommand implements Command{
    private final static String PARAM_ID_BOOK = "id_book";
    private final static String PARAM_ISBN = "isbn";
    private final static String PARAM_TITTLE = "tittle";
    private final static String PARAM_AUTHOR_SURNAME = "surname";
    private final static String PARAM_AUTHOR_NAME = "name";
    private final static String PARAM_AUTHOR_MIDDLE_NAME = "middle_name";
    private final static String PARAM_AUTHOR_COUNTRY = "country";
    private final static String PARAM_GENRE = "genre";
    private final static String PARAM_DATA_EDITION = "date_edition";
    private final static String PARAM_PLACE_EDITION = "place_edition";
    private final static String PARAM_PUBLISHER = "publisher";
    private final static String PARAM_NUMBER_COPIES = "number_copies";
    private final static String PARAM_IMAGE = "image";
    private BookLogic bookLogic;

    public UpdateBookCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Book book = new Book();
        Author author = new Author();
        List<Book> listBooks;

        book.setId(Integer.parseInt(request.getParameter(PARAM_ID_BOOK)));
        book.setIsbn(request.getParameter(PARAM_ISBN));
        book.setTittle(request.getParameter(PARAM_TITTLE));
        author.setSurname(request.getParameter(PARAM_AUTHOR_SURNAME));
        author.setName(request.getParameter(PARAM_AUTHOR_NAME));
        author.setMiddleName(request.getParameter(PARAM_AUTHOR_MIDDLE_NAME));
        author.setCountryBirth(request.getParameter(PARAM_AUTHOR_COUNTRY));
        book.setAuthor(author);
        book.setDateEdition(Date.valueOf(request.getParameter(PARAM_DATA_EDITION)));
        book.setPlaceEdition(request.getParameter(PARAM_PLACE_EDITION));
        book.setPublisher(request.getParameter(PARAM_PUBLISHER));
        book.setNumberCopies(Integer.parseInt(request.getParameter(PARAM_NUMBER_COPIES)));

        try {
            if (bookLogic.updateBook(book)) {
                listBooks = bookLogic.getBooks();
                request.setAttribute("books", listBooks);
                //success message
                page = ADMIN_BOOKS_PAGE;
            }
            return page;
        } catch (DAOException e) {
            throw new CommandException(e);
        }
    }
}
