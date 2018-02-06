package by.radomskaya.project.command.user.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.MessageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.constant.PropertyKeys;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;
import by.radomskaya.project.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindBookByTittleCommand implements Command {
    private BookLogic bookLogic;

    public FindBookByTittleCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page;
        List<Book> listFoundBooksByTittle;

        try {
            String tittle = request.getParameter(ParameterConstants.PARAM_BOOK);
            listFoundBooksByTittle = bookLogic.getFoundBooksByTittle(tittle);

            if (listFoundBooksByTittle.isEmpty()) {
                request.setAttribute(MessageConstants.MESSAGE_FIND_BOOK, MessageManager.getLocale(locale).getMessage(PropertyKeys.FIND_BOOKS_MESSAGE));
                page = JspPageConstants.USER_FIND_BOOKS_PAGE;
            } else {
                request.setAttribute("foundBooks", listFoundBooksByTittle);
                page = JspPageConstants.USER_FIND_BOOKS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
