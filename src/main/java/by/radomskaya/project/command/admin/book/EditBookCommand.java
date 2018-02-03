package by.radomskaya.project.command.admin.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.PageConstant;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class EditBookCommand implements Command {
    private BookLogic bookLogic;

    public EditBookCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page;
        Book book;
        Map<Integer, String> mapGenres;

        try {
            int idBook = Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID_BOOK));

            book = bookLogic.getBookById(idBook);
            request.setAttribute("book", book);
            mapGenres = bookLogic.getAllGenres();
            request.setAttribute("genres", mapGenres);

            page = PageConstant.ADMIN_EDIT_BOOK_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
