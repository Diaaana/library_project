package by.radomskaya.project.command.admin;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Librarian;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AdminLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.*;

public class ShowLibrariansCommand implements Command {
    private AdminLogic adminLogic;

    public ShowLibrariansCommand(AdminLogic adminLogic) {
        this.adminLogic = adminLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        List<Librarian> listLibrarians;

        try {
            listLibrarians = adminLogic.getLibrarians();
            request.setAttribute("librarians", listLibrarians);
            page = ADMIN_LIBRARIANS_PAGE;
        } catch (DAOException e) {
            e.printStackTrace();//!!!
        }

        return page;
    }
}
