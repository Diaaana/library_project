package by.radomskaya.project.command.user.book;

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
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetBooksCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(GetBooksCommand.class);
    private BookLogic bookLogic;

    public GetBooksCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router = new Router();
        String page = null;
        List<Book> listBooks;
        int bookPage = 1;

        if (request.getParameter(ParameterConstants.PARAM_PAGE) != null) {
            bookPage = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_PAGE));
        }

        try {
            listBooks = bookLogic.getBooksWithPages(bookPage);
            request.setAttribute(ParameterConstants.PARAM_NUMBER_OF_PAGES, bookLogic.getNoOfPages());
            session.setAttribute(ParameterConstants.PARAM_BOOKS, listBooks);
            session.setAttribute(ParameterConstants.PARAM_CURRENT_PAGE, bookPage);
            page = JspPageConstants.USER_BOOKS_PAGE;
        } catch (LogicException e) {
            LOGGER.error(e);
        }
        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
