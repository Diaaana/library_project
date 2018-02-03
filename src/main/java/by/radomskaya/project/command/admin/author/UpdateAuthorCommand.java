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
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdateAuthorCommand implements Command {
    private AuthorLogic authorLogic;

    public UpdateAuthorCommand(AuthorLogic authorLogic) {
        this.authorLogic = authorLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String page = null;
        Author author;
        List<Author> listAuthors;

        try {
            author = setAuthorFromRequest(request);

            if (authorLogic.updateAuthor(author)) {
                listAuthors = authorLogic.getAuthors();
                session.setAttribute("authors", listAuthors);
                request.setAttribute("messageEdit", "success");
                page = PageConstant.ADMIN_AUTHORS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.REDIRECT);
        return router;
    }

    private Author setAuthorFromRequest(HttpServletRequest request) {
        Author author = new Author();
        author.setId(Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID_AUTHOR)));
        author.setSurname(request.getParameter(RequestParameter.PARAM_AUTHOR_SURNAME));
        author.setName(request.getParameter(RequestParameter.PARAM_AUTHOR_NAME));
        String middleName = request.getParameter(RequestParameter.PARAM_AUTHOR_MIDDLE_NAME);
        if (middleName.equals(RequestParameter.PARAM_AUTHOR_EMPTY_MIDDLE_NAME)) {
            author.setMiddleName(RequestParameter.PARAM_AUTHOR_NO_MIDDLE_NAME);
        } else {
            author.setMiddleName(middleName);
        }
        author.setCountryBirth(request.getParameter(RequestParameter.PARAM_AUTHOR_COUNTRY));
        return author;
    }
}
