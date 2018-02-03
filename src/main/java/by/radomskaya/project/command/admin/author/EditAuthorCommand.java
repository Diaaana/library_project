package by.radomskaya.project.command.admin.author;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.PageConstant;
import by.radomskaya.project.constant.RequestParameter;
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
        String page = null;
        Author author;

        try {
            int idAuthor = Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID_AUTHOR));

            author = authorLogic.getAuthorById(idAuthor);
            request.setAttribute("author", author);
            page = PageConstant.ADMIN_EDIT_AUTHOR_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
