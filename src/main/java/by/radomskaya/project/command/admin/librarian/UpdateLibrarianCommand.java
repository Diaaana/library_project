package by.radomskaya.project.command.admin.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Librarian;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.LibrarianLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.ADMIN_LIBRARIANS_PAGE;

public class UpdateLibrarianCommand implements Command {
    private final static String PARAM_ID_LIBRARIAN = "id_librarian";
    private final static String PARAM_LIBRARIAN_SURNAME = "surname";
    private final static String PARAM_LIBRARIAN_NAME = "name";
    private final static String PARAM_LIBRARIAN_MIDDLE_NAME = "middle_name";
    private final static String PARAM_LIBRARIAN_SHIFT = "shift";
    private LibrarianLogic librarianLogic;

    public UpdateLibrarianCommand(LibrarianLogic librarianLogic) {
        this.librarianLogic = librarianLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Librarian librarian = new Librarian();
        List<Librarian> listLibrarians;

        librarian.setId(Integer.parseInt(request.getParameter(PARAM_ID_LIBRARIAN)));
        librarian.setSurname(request.getParameter(PARAM_LIBRARIAN_SURNAME));
        librarian.setName(request.getParameter(PARAM_LIBRARIAN_NAME));
        librarian.setMiddleName(request.getParameter(PARAM_LIBRARIAN_MIDDLE_NAME));
        librarian.setShift(Integer.parseInt(request.getParameter(PARAM_LIBRARIAN_SHIFT)));

        try {
            if (librarianLogic.updateLibrarian(librarian)) {
                //success message
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
