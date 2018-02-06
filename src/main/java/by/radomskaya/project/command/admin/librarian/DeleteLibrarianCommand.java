package by.radomskaya.project.command.admin.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.MessageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.constant.PropertyKeys;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.LibrarianLogic;
import by.radomskaya.project.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteLibrarianCommand implements Command {
    private LibrarianLogic librarianLogic;

    public DeleteLibrarianCommand(LibrarianLogic librarianLogic) {
        this.librarianLogic = librarianLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page = null;
        List<User> listLibrarians;

        try {
            int id = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_LIBRARIAN));

            if (librarianLogic.deleteLibrarian(id)) {
                listLibrarians = librarianLogic.getLibrarians();
                request.setAttribute("librarians", listLibrarians);
                request.setAttribute(MessageConstants.MESSAGE_DELETE_LIBRARIAN, MessageManager.getLocale(locale).getMessage(PropertyKeys.DELETE_LIBRARIAN_MESSAGE));
                page = JspPageConstants.ADMIN_LIBRARIANS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
