package by.radomskaya.project.command.admin;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AdminLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.ADMIN_AUTHORS_PAGE;

public class ShowAuthorsCommand implements Command {
    private AdminLogic adminLogic;

    public ShowAuthorsCommand(AdminLogic adminLogic) {
        this.adminLogic = adminLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        List<Author> listAuthors;

        try {
            listAuthors = adminLogic.getAuthors();
            request.setAttribute("authors", listAuthors);
            page = ADMIN_AUTHORS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        return page;
    }
}