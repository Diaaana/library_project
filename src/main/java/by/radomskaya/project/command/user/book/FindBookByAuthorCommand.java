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

public class FindBookByAuthorCommand implements Command {
    private BookLogic bookLogic;

    public FindBookByAuthorCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page;
        List<Book> listFoundBooksByAuthor;

        try {
            String author = request.getParameter(RequestParameter.PARAM_AUTHOR);
            listFoundBooksByAuthor = bookLogic.getFoundBooksByAuthor(author);

            if (listFoundBooksByAuthor.isEmpty()) {
                request.setAttribute("messageFindBook", "empty");
                page = JspPage.USER_FIND_BOOKS_PAGE;
            } else {
                request.setAttribute("foundBooks", listFoundBooksByAuthor);
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
