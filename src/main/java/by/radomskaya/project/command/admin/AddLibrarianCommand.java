package by.radomskaya.project.command.admin;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Librarian;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.LibrarianLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.ADMIN_ADD_LIBRARIANS_PAGE;
import static by.radomskaya.project.constant.PageConstant.ADMIN_LIBRARIANS_PAGE;

public class AddLibrarianCommand implements Command {
    private final static String PARAM_SURNAME = "surname";
    private final static String PARAM_NAME = "name";
    private final static String PARAM_MIDDLE_NAME = "middle_name";
    private final static String PARAM_SHIFT = "shift";
    private final static String PARAM_LOGIN = "login";
    private final static String PARAM_PASSWORD = "password";
    private LibrarianLogic librarianLogic;

    public AddLibrarianCommand(LibrarianLogic librarianLogic) {
        this.librarianLogic = librarianLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        List<Librarian> listLibrarians;
        Librarian librarian = new Librarian();

        librarian.setSurname(request.getParameter(PARAM_SURNAME));
        librarian.setName(request.getParameter(PARAM_NAME));
        librarian.setMiddleName(request.getParameter(PARAM_MIDDLE_NAME));
        librarian.setShift(Integer.parseInt(request.getParameter(PARAM_SHIFT)));
        librarian.setLogin(request.getParameter(PARAM_LOGIN));
        librarian.setPassword(request.getParameter(PARAM_PASSWORD));

        try {
            if (librarianLogic.addLibrarian(librarian)) {
                listLibrarians = librarianLogic.getLibrarians();
                request.setAttribute("librarians", listLibrarians);
                page = ADMIN_LIBRARIANS_PAGE;
            } else {
                page = ADMIN_ADD_LIBRARIANS_PAGE; //TO DO
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
