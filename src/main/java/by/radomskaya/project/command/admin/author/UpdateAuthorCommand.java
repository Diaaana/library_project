package by.radomskaya.project.command.admin.author;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPage;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AuthorLogic;
import by.radomskaya.project.validation.InputParamValidator;

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
                page = JspPage.ADMIN_AUTHORS_PAGE;
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
        String surname = request.getParameter(RequestParameter.PARAM_AUTHOR_SURNAME);
        String name = request.getParameter(RequestParameter.PARAM_AUTHOR_NAME);
        String middleName = request.getParameter(RequestParameter.PARAM_AUTHOR_MIDDLE_NAME);
        String country = request.getParameter(RequestParameter.PARAM_AUTHOR_COUNTRY);
        if (middleName.equals(RequestParameter.PARAM_AUTHOR_EMPTY_MIDDLE_NAME)) {
            middleName = RequestParameter.PARAM_AUTHOR_NO_MIDDLE_NAME;
            author.setMiddleName(middleName);
        } else {
            if (InputParamValidator.isValidateMiddleName(middleName)) {
                author.setMiddleName(middleName);
            }
        }

        if (InputParamValidator.isValidateAuthorData(surname, name, country)) {
            author.setSurname(surname);
            author.setName(name);
            author.setCountryBirth(country);
        }
        return author;
    }
}
