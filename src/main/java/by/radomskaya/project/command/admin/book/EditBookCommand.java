package by.radomskaya.project.command.admin.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.LogicException;
import by.radomskaya.project.logic.BookLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class EditBookCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EditBookCommand.class);
    private BookLogic bookLogic;

    public EditBookCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        Book book;
        Map<Integer, String> mapGenres;

        try {
            int idBook = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_BOOK));

            book = bookLogic.getBookById(idBook);
            request.setAttribute(ParameterConstants.PARAM_BOOK, book);
            mapGenres = bookLogic.getAllGenres();
            request.setAttribute(ParameterConstants.PARAM_GENRES, mapGenres);

            page = JspPageConstants.ADMIN_EDIT_BOOK_PAGE;
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
