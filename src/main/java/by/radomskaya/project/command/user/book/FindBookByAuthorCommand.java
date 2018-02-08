package by.radomskaya.project.command.user.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.MessageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.constant.PropertyKeys;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.LogicException;
import by.radomskaya.project.logic.BookLogic;
import by.radomskaya.project.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindBookByAuthorCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(FindBookByAuthorCommand.class);
    private BookLogic bookLogic;

    public FindBookByAuthorCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page = null;
        List<Book> listFoundBooksByAuthor;

        try {
            String author = request.getParameter(ParameterConstants.PARAM_AUTHOR);
            listFoundBooksByAuthor = bookLogic.getFoundBooksByAuthor(author);

            if (listFoundBooksByAuthor.isEmpty()) {
                request.setAttribute(MessageConstants.MESSAGE_FIND_BOOK, MessageManager.getLocale(locale).getMessage(PropertyKeys.FIND_BOOKS_MESSAGE));
                page = JspPageConstants.USER_FIND_BOOKS_PAGE;
            } else {
                request.setAttribute(ParameterConstants.PARAM_FOUND_BOOKS, listFoundBooksByAuthor);
                page = JspPageConstants.USER_FIND_BOOKS_PAGE;
            }
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
