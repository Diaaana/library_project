package by.radomskaya.project.command.admin;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AuthorLogic;
import by.radomskaya.project.logic.BookLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;

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
    private final static String PARAM_IMAGE = "image";
    private BookLogic bookLogic;
    private AuthorLogic authorLogic;

    public AddBookCommand(BookLogic bookLogic, AuthorLogic authorLogic) {
        this.bookLogic = bookLogic;
        this.authorLogic = authorLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Book book = new Book();
        Author author = new Author();
        try {
            book.setIsbn(request.getParameter(PARAM_ISBN));
            book.setTittle(request.getParameter(PARAM_TITTLE));
            book.setGenre(request.getParameter(PARAM_GENRE));
            book.setDateEdition(request.getParameter(PARAM_DATA_EDITION));
            book.setPlaceEdition(request.getParameter(PARAM_PLACE_EDITION));
            book.setPublisher(request.getParameter(PARAM_PUBLISHER));
            book.setNumberCopies(Integer.parseInt(request.getParameter(PARAM_NUMBER_COPIES)));
            // book.setImage(request.getParameter(PARAM_IMAGE));
            Part part = request.getPart(request.getParameter(PARAM_IMAGE));
            String imageName = getImageName(part);
            String webPath = request.getServletContext().getRealPath("/");
            book.setImage(imageName);
            author.setSurname(request.getParameter(PARAM_AUTHOR_SURNAME));
            author.setName(request.getParameter(PARAM_AUTHOR_NAME));
            author.setMiddleName(request.getParameter(PARAM_AUTHOR_MIDDLE_NAME));
            author.setCountryBirth(request.getParameter(PARAM_AUTHOR_COUNTRY));

            if (bookLogic.addBook(book) && authorLogic.addAuthor(author) && bookLogic.addGenre(book)) {
                request.setAttribute("success", "Все хорошо");
                page = ADMIN_BOOKS_PAGE;
            } else {
                page = ADMIN_ADD_BOOKS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        } catch (IOException|ServletException e) {
            throw new CommandException(e);
        }

        return page;
    }

    private String getImageName(Part filePart) {
        String name = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        return name;
    }
}
