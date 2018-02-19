package by.radomskaya.project.command.admin;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Librarian;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AdminLogic;

import javax.servlet.http.HttpServletRequest;

import static by.radomskaya.project.constant.PageConstant.ADMIN_LIBRARIANS_PAGE;

public class DeleteLibrarianCommand implements Command {
    private AdminLogic adminLogic;

    public DeleteLibrarianCommand(AdminLogic adminLogic) {
        this.adminLogic = adminLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Librarian librarian = new Librarian();
        try {
            if (adminLogic.addLibrarian(librarian)) {
                page = ADMIN_LIBRARIANS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
