package by.radomskaya.project.command.admin.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.PageConstant;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.LibrarianLogic;

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
                page = PageConstant.ADMIN_LIBRARIANS_PAGE;
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
        librarian.setNumberTicket(Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID_LIBRARIAN)));
        librarian.setSurname(request.getParameter(RequestParameter.PARAM_SURNAME));
        librarian.setName(request.getParameter(RequestParameter.PARAM_NAME));
        librarian.setMiddleName(request.getParameter(RequestParameter.PARAM_MIDDLE_NAME));
        librarian.setLogin(request.getParameter(RequestParameter.PARAM_LOGIN));
        return librarian;
    }
}
