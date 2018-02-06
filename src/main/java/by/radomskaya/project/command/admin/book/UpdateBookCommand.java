package by.radomskaya.project.command.admin.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;
import by.radomskaya.project.validation.InputParamValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

public class UpdateBookCommand implements Command {
    private BookLogic bookLogic;

    public UpdateBookCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String page = null;
        Book book;
        List<Book> listBooks;

        try {
            int idBook = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_BOOK));
            String[] genres = request.getParameterValues(ParameterConstants.PARAM_GENRE);

            book = setBookFromRequest(request);

            if (bookLogic.updateBook(book)) {
                bookLogic.updateBookAndGenre(genres, idBook);
                listBooks = bookLogic.getBooks();
                session.setAttribute("books", listBooks);
                page = JspPageConstants.ADMIN_BOOKS_PAGE;
            }

        } catch (DAOException | ServletException | IOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.REDIRECT);
        return router;
    }

    private Book setBookFromRequest(HttpServletRequest request) throws IOException, ServletException {
        Book book = new Book();
        Author author;
        int id = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_BOOK));
        String isbn = request.getParameter(ParameterConstants.PARAM_ISBN);
        String tittle = request.getParameter(ParameterConstants.PARAM_TITTLE);
        author = setAuthorFromRequest(request);
        Date dateEdition = Date.valueOf(request.getParameter(ParameterConstants.PARAM_DATA_EDITION));
        String placeEdition = request.getParameter(ParameterConstants.PARAM_PLACE_EDITION);
        String publisher = request.getParameter(ParameterConstants.PARAM_PUBLISHER);
        int numberCopies = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_NUMBER_COPIES));
        Part filePart = request.getPart(ParameterConstants.PARAM_IMAGE);
        String imageName = getImageName(filePart);
        if (imageName.equals(ParameterConstants.PARAM_EMPTY_IMAGE)) {
            book.setImage(request.getParameter(ParameterConstants.PARAM_OLD_IMAGE));
        } else {
            book.setImage(imageName);
        }

        if (InputParamValidator.isValidateBookData(isbn, tittle, dateEdition, placeEdition, publisher, numberCopies)) {
            book.setIsbn(isbn);
            book.setTittle(tittle);
            book.setAuthor(author);
            book.setDateEdition(dateEdition);
            book.setPlaceEdition(placeEdition);
            book.setPublisher(publisher);
            book.setNumberCopies(numberCopies);
        }

        return book;
    }

    private Author setAuthorFromRequest(HttpServletRequest request) {
        Author author = new Author();
        String surname = request.getParameter(ParameterConstants.PARAM_AUTHOR_SURNAME);
        String name = request.getParameter(ParameterConstants.PARAM_AUTHOR_NAME);
        String middleName = request.getParameter(ParameterConstants.PARAM_AUTHOR_MIDDLE_NAME);
        String country = request.getParameter(ParameterConstants.PARAM_AUTHOR_COUNTRY);
        if (middleName.equals(ParameterConstants.PARAM_AUTHOR_EMPTY_MIDDLE_NAME)) {
            middleName = ParameterConstants.PARAM_AUTHOR_NO_MIDDLE_NAME;
            author.setMiddleName(middleName);
        } else {
            if (InputParamValidator.isValidateMiddleName(middleName)) {
                author.setMiddleName(middleName);
            }
        }

        if (InputParamValidator.isValidateAuthorData(surname, name, country)) {
            author.setSurname(surname);
            author.setName(name);
            author.setCountryBirth(country);
        }
        return author;
    }

    private String getImageName(Part filePart) {
        String name = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        return name;
    }
}
