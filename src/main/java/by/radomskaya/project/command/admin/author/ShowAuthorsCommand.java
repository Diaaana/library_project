package by.radomskaya.project.command.admin.author;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPage;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AuthorLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAuthorsCommand implements Command {
    private AuthorLogic authorLogic;

    public ShowAuthorsCommand(AuthorLogic authorLogic) {
        this.authorLogic = authorLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page;
        List<Author> listAuthors;

        try {
            listAuthors = authorLogic.getAuthors();
            request.setAttribute("authors", listAuthors);
            page = JspPage.ADMIN_AUTHORS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
