package by.radomskaya.project.command.user.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;

import javax.servlet.http.HttpServletRequest;

import static by.radomskaya.project.constant.JspPage.USER_PERSONAL_BOOK_PAGE;

public class GetPersonalBookCommand implements Command {
    private BookLogic bookLogic;

    public GetPersonalBookCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        Book book;

        try {
            int idBook = Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID_BOOK));
            book = bookLogic.getBookById(idBook);
            System.out.println(book);
            request.setAttribute("personalBook", book);
            page = USER_PERSONAL_BOOK_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
