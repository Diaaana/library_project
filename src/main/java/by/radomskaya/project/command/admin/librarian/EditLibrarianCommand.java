package by.radomskaya.project.command.admin.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.LibrarianLogic;

import javax.servlet.http.HttpServletRequest;

public class EditLibrarianCommand implements Command {
    private LibrarianLogic librarianLogic;

    public EditLibrarianCommand(LibrarianLogic librarianLogic) {
        this.librarianLogic = librarianLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page;
        User librarian;

        try {
            int idLibrarian = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_LIBRARIAN));
            librarian = librarianLogic.getLibrarianById(idLibrarian);

            request.setAttribute("librarian", librarian);
            page = JspPageConstants.ADMIN_EDIT_LIBRARIAN_PAGE;

        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
