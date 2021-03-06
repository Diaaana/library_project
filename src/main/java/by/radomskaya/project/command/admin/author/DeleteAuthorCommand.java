package by.radomskaya.project.command.admin.author;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.MessageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.constant.PropertyKeys;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.LogicException;
import by.radomskaya.project.logic.AuthorLogic;
import by.radomskaya.project.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteAuthorCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteAuthorCommand.class);
    private AuthorLogic authorLogic;

    public DeleteAuthorCommand(AuthorLogic authorLogic) {
        this.authorLogic = authorLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page = null;
        List<Author> listAuthors;

        try {
            int idAuthor = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_AUTHOR));

            if (authorLogic.deleteAuthor(idAuthor)) {
                listAuthors = authorLogic.getAuthors();
                request.setAttribute(ParameterConstants.PARAM_AUTHORS, listAuthors);
                request.setAttribute(MessageConstants.MESSAGE_DELETE_AUTHOR, MessageManager.getLocale(locale).getMessage(PropertyKeys.DELETE_AUTHOR_MESSAGE));
                page = JspPageConstants.ADMIN_AUTHORS_PAGE;
            } else {
                page = JspPageConstants.ADMIN_AUTHORS_PAGE;
            }

        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
