package by.radomskaya.project.command.user.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPage;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;

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
        String page = null;
        List<Book> listFoundBooksByTittle;

        try {
            String tittle = request.getParameter(RequestParameter.PARAM_BOOK);
            listFoundBooksByTittle = bookLogic.getFoundBooksByTittle(tittle);

            if (listFoundBooksByTittle.isEmpty()) {
                request.setAttribute("messageFindBook", "empty");
                page = JspPage.USER_FIND_BOOKS_PAGE;
            } else {
                request.setAttribute("foundBooks", listFoundBooksByTittle);
                page = JspPage.USER_FIND_BOOKS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
