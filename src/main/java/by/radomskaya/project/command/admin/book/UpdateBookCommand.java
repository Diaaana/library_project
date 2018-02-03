package by.radomskaya.project.command.admin.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.PageConstant;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;

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
            int idBook = Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID_BOOK));
            String[] genres = request.getParameterValues(RequestParameter.PARAM_GENRE);

            book = setBookFromRequest(request);

            if (bookLogic.updateBook(book)) {
                bookLogic.updateBookAndGenre(genres, idBook);
                listBooks = bookLogic.getBooks();
                session.setAttribute("books", listBooks);
                page = PageConstant.ADMIN_BOOKS_PAGE;
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
        book.setId(Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID_BOOK)));
        book.setIsbn(request.getParameter(RequestParameter.PARAM_ISBN));
        book.setTittle(request.getParameter(RequestParameter.PARAM_TITTLE));
        book.setAuthor(setAuthorFromRequest(request));
        book.setDateEdition(Date.valueOf(request.getParameter(RequestParameter.PARAM_DATA_EDITION)));
        book.setPlaceEdition(request.getParameter(RequestParameter.PARAM_PLACE_EDITION));
        book.setPublisher(request.getParameter(RequestParameter.PARAM_PUBLISHER));
        book.setNumberCopies(Integer.parseInt(request.getParameter(RequestParameter.PARAM_NUMBER_COPIES)));
        Part filePart = request.getPart(RequestParameter.PARAM_IMAGE);
        String imageName = getImageName(filePart);
        if (imageName.equals(RequestParameter.PARAM_EMPTY_IMAGE)) {
            book.setImage(request.getParameter(RequestParameter.PARAM_OLD_IMAGE));
        } else {
            book.setImage(imageName);
        }
        return book;
    }

    private Author setAuthorFromRequest(HttpServletRequest request) {
        Author author = new Author();
        author.setSurname(request.getParameter(RequestParameter.PARAM_AUTHOR_SURNAME));
        author.setName(request.getParameter(RequestParameter.PARAM_AUTHOR_NAME));
        String middleName = request.getParameter(RequestParameter.PARAM_AUTHOR_MIDDLE_NAME);
        if (middleName.equals(RequestParameter.PARAM_AUTHOR_EMPTY_MIDDLE_NAME)) {
            author.setMiddleName(RequestParameter.PARAM_AUTHOR_NO_MIDDLE_NAME);
        } else {
            author.setMiddleName(middleName);
        }
        author.setCountryBirth(request.getParameter(RequestParameter.PARAM_AUTHOR_COUNTRY));
        return author;
    }

    private String getImageName(Part filePart) {
        String name = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        return name;
    }
}
