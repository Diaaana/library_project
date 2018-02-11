package by.radomskaya.project.command.admin.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPage;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.LibrarianLogic;
import by.radomskaya.project.validation.InputParamValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class UpdateLibrarianCommand implements Command {
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
                session.setAttribute("librarians", listLibrarians);
                request.setAttribute("messageUpdate", "success");
                page = JspPage.ADMIN_LIBRARIANS_PAGE;
            }

        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.REDIRECT);
        return router;
    }

    private User setLibrarianFromRequest(HttpServletRequest request) {
        User librarian = new User();
        int id = Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID_LIBRARIAN));
        String surname = request.getParameter(RequestParameter.PARAM_SURNAME);
        String name = request.getParameter(RequestParameter.PARAM_NAME);
        String middleName = request.getParameter(RequestParameter.PARAM_MIDDLE_NAME);
        String login = request.getParameter(RequestParameter.PARAM_LOGIN);

        if (InputParamValidator.isValidateLibrarianData(surname, name, middleName, login)) {
            librarian = new User(id, surname, name, middleName, login);
        }

        return librarian;
    }
}
