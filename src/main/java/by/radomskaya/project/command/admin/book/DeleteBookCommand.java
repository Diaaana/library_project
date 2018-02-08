package by.radomskaya.project.command.admin.book;

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
import javax.servlet.http.HttpSession;
import java.util.List;

public class DeleteBookCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteBookCommand.class);
    private BookLogic bookLogic;

    public DeleteBookCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page = null;
        List<Book> listBooks;
        int bookPage = 1;

        if (request.getParameter(ParameterConstants.PARAM_PAGE) != null) {
            bookPage = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_PAGE));
        }

        try {
            int idBook = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_BOOK));

            if (bookLogic.deleteBook(idBook)) {
                listBooks = bookLogic.getBooksWithPages(bookPage);
                request.setAttribute(ParameterConstants.PARAM_BOOKS, listBooks);
                session.setAttribute(ParameterConstants.PARAM_NUMBER_OF_PAGES, bookLogic.getNoOfPages());
                request.setAttribute(MessageConstants.MESSAGE_DELETE_BOOK, MessageManager.getLocale(locale).getMessage(PropertyKeys.DELETE_BOOK_MESSAGE));
                page = JspPageConstants.ADMIN_BOOKS_PAGE;
            }
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
