package by.radomskaya.project.command.admin.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Librarian;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.LibrarianLogic;

import javax.servlet.http.HttpServletRequest;

import static by.radomskaya.project.constant.PageConstant.ADMIN_EDIT_LIBRARIAN_PAGE;

public class EditLibrarianCommand implements Command {
    private final String PARAM_ID_LIBRARIAN = "id_librarian";
    private LibrarianLogic librarianLogic;

    public EditLibrarianCommand(LibrarianLogic librarianLogic) {
        this.librarianLogic = librarianLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        int idLibrarian = Integer.parseInt(request.getParameter(PARAM_ID_LIBRARIAN));
        Librarian librarian;

        try {
            librarian = librarianLogic.getLibrarianById(idLibrarian);
            request.setAttribute("librarian", librarian);
            page = ADMIN_EDIT_LIBRARIAN_PAGE;

        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
