package by.radomskaya.project.command.admin;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AdminLogic;

import javax.servlet.http.HttpServletRequest;

import static by.radomskaya.project.constant.PageConstant.ADMIN_ADD_BOOKS_PAGE;
import static by.radomskaya.project.constant.PageConstant.ADMIN_BOOKS_PAGE;

public class AddBookCommand implements Command {
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
    private AdminLogic addLogic;

    public AddBookCommand(AdminLogic addLogic) {
        this.addLogic = addLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        Book book = new Book();
        Author author = new Author();
        book.setIsbn(request.getParameter(PARAM_ISBN));
        book.setTittle(request.getParameter(PARAM_TITTLE));
        book.setGenre(request.getParameter(PARAM_GENRE));
        book.setDateEdition(request.getParameter(PARAM_DATA_EDITION));
        book.setPlaceEdition(request.getParameter(PARAM_PLACE_EDITION));
        book.setPublisher(request.getParameter(PARAM_PUBLISHER));
        book.setNumberCopies(Integer.parseInt(request.getParameter(PARAM_NUMBER_COPIES)));
        author.setSurname(request.getParameter(PARAM_AUTHOR_SURNAME));
        author.setName(request.getParameter(PARAM_AUTHOR_NAME));
        author.setMiddleName(request.getParameter(PARAM_AUTHOR_MIDDLE_NAME));
        author.setCountryBirth(request.getParameter(PARAM_AUTHOR_COUNTRY));

        try {
            if (addLogic.addBook(book) && addLogic.addAuthor(author) && addLogic.addGenre(book)) {
                page = ADMIN_BOOKS_PAGE;
            } else {
                page = ADMIN_ADD_BOOKS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
