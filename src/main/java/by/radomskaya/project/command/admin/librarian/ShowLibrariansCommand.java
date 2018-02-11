package by.radomskaya.project.command.admin.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPage;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.LibrarianLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowLibrariansCommand implements Command {
    private LibrarianLogic librarianLogic;

    public ShowLibrariansCommand(LibrarianLogic librarianLogic) {
        this.librarianLogic = librarianLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        List<User> listLibrarians;

        try {
            listLibrarians = librarianLogic.getLibrarians();

            request.setAttribute("librarians", listLibrarians);
            page = JspPage.ADMIN_LIBRARIANS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
