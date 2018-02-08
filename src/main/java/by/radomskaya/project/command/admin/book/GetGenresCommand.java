package by.radomskaya.project.command.admin.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.LogicException;
import by.radomskaya.project.logic.BookLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static by.radomskaya.project.constant.JspPageConstants.ADMIN_ADD_BOOKS_PAGE;

public class GetGenresCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(GetGenresCommand.class);
    private BookLogic bookLogic;

    public GetGenresCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        Map<Integer, String> mapGenres;

        try {
            mapGenres = bookLogic.getAllGenres();
            request.setAttribute(ParameterConstants.PARAM_GENRES, mapGenres);
            page = ADMIN_ADD_BOOKS_PAGE;
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
