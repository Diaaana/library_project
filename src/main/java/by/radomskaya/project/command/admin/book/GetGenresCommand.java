package by.radomskaya.project.command.admin.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static by.radomskaya.project.constant.PageConstant.ADMIN_ADD_BOOKS_PAGE;

public class GetGenresCommand implements Command {
    private BookLogic bookLogic;

    public GetGenresCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page;
        Map<Integer, String> mapGenres;

        try {
            mapGenres = bookLogic.getAllGenres();
            request.setAttribute("genres", mapGenres);
            page = ADMIN_ADD_BOOKS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
