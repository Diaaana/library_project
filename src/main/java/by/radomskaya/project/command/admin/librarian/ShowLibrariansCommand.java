package by.radomskaya.project.command.admin.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.LibrarianLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.ADMIN_LIBRARIANS_PAGE;

public class ShowLibrariansCommand implements Command {
    private LibrarianLogic librarianLogic;

    public ShowLibrariansCommand(LibrarianLogic librarianLogic) {
        this.librarianLogic = librarianLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        List<User> listLibrarians;

        try {
            listLibrarians = librarianLogic.getLibrarians();
            request.setAttribute("librarians", listLibrarians);
            page = ADMIN_LIBRARIANS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
