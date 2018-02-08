package by.radomskaya.project.command.admin.author;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.LogicException;
import by.radomskaya.project.logic.AuthorLogic;
import by.radomskaya.project.validation.InputParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdateAuthorCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(UpdateAuthorCommand.class);
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
                session.setAttribute(ParameterConstants.PARAM_AUTHORS, listAuthors);
                router.setRoute(Router.RouteType.REDIRECT);
                page = JspPageConstants.ADMIN_AUTHORS_PAGE;
            } else {
                router.setRoute(Router.RouteType.FORWARD);
                page = JspPageConstants.ADMIN_EDIT_AUTHOR_PAGE;
            }
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        return router;
    }

    private Author setAuthorFromRequest(HttpServletRequest request) {
        Author author = new Author();
        int idAuthor = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_AUTHOR));
        String surname = request.getParameter(ParameterConstants.PARAM_AUTHOR_SURNAME);
        String name = request.getParameter(ParameterConstants.PARAM_AUTHOR_NAME);
        String middleName = request.getParameter(ParameterConstants.PARAM_AUTHOR_MIDDLE_NAME);
        String country = request.getParameter(ParameterConstants.PARAM_AUTHOR_COUNTRY);
        if (middleName.equals(ParameterConstants.PARAM_AUTHOR_EMPTY_MIDDLE_NAME)) {
            author.setMiddleName(ParameterConstants.PARAM_AUTHOR_NO_MIDDLE_NAME);
        } else if (middleName.equals(ParameterConstants.PARAM_AUTHOR_NO_MIDDLE_NAME)) {
            author.setMiddleName(ParameterConstants.PARAM_AUTHOR_NO_MIDDLE_NAME);
        } else if (InputParamValidator.isValidateMiddleName(middleName)) {
            author.setMiddleName(middleName);
        }

        if (InputParamValidator.isValidateAuthorData(surname, name, country)) {
            author.setId(idAuthor);
            author.setSurname(surname);
            author.setName(name);
            author.setCountryBirth(country);
        }

        return author;
    }
}
