package by.radomskaya.project.command.admin.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Librarian;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.LibrarianLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.ADMIN_LIBRARIANS_PAGE;

public class DeleteLibrarianCommand implements Command {
    private final String PARAM_ID_LIBRARIAN = "id_librarian";
    private LibrarianLogic librarianLogic;

    public DeleteLibrarianCommand(LibrarianLogic librarianLogic) {
        this.librarianLogic = librarianLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        List<Librarian> listLibrarians;
        int id = Integer.parseInt(request.getParameter(PARAM_ID_LIBRARIAN));

        try {
            if (librarianLogic.deleteLibrarian(id)) {
                listLibrarians = librarianLogic.getLibrarians();
                request.setAttribute("librarians", listLibrarians);
                page = ADMIN_LIBRARIANS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
