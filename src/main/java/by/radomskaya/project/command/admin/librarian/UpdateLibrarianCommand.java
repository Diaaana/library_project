package by.radomskaya.project.command.admin.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.LogicException;
import by.radomskaya.project.logic.LibrarianLogic;
import by.radomskaya.project.validation.InputParamValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdateLibrarianCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(UpdateLibrarianCommand.class);
    private LibrarianLogic librarianLogic;

    public UpdateLibrarianCommand(LibrarianLogic librarianLogic) {
        this.librarianLogic = librarianLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String page = null;
        User librarian;
        List<User> listLibrarians;

        try {
            librarian = setLibrarianFromRequest(request);

            if (librarianLogic.updateLibrarian(librarian)) {
                listLibrarians = librarianLogic.getLibrarians();
                session.setAttribute(ParameterConstants.PARAM_LIBRARIANS, listLibrarians);
                router.setRoute(Router.RouteType.REDIRECT);
                page = JspPageConstants.ADMIN_LIBRARIANS_PAGE;
            } else {
                router.setRoute(Router.RouteType.FORWARD);
                page = JspPageConstants.ADMIN_EDIT_LIBRARIAN_PAGE;
            }

        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        return router;
    }

    private User setLibrarianFromRequest(HttpServletRequest request) {
        User librarian = new User();
        int id = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_LIBRARIAN));
        String surname = request.getParameter(ParameterConstants.PARAM_SURNAME);
        String name = request.getParameter(ParameterConstants.PARAM_NAME);
        String middleName = request.getParameter(ParameterConstants.PARAM_MIDDLE_NAME);
        String login = request.getParameter(ParameterConstants.PARAM_LOGIN);

        if (InputParamValidator.isValidateLibrarianData(surname, name, middleName, login)) {
            librarian = new User(id, surname, name, middleName, login);
        }

        return librarian;
    }
}
