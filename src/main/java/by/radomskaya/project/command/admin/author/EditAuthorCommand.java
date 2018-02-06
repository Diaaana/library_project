package by.radomskaya.project.command.admin.author;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AuthorLogic;

import javax.servlet.http.HttpServletRequest;

public class EditAuthorCommand implements Command {
    private AuthorLogic authorLogic;

    public EditAuthorCommand(AuthorLogic authorLogic) {
        this.authorLogic = authorLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page;
        Author author;

        try {
            int idAuthor = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_AUTHOR));

            author = authorLogic.getAuthorById(idAuthor);
            request.setAttribute("author", author);
            page = JspPageConstants.ADMIN_EDIT_AUTHOR_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
