package by.radomskaya.project.command.admin;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AdminLogic;

import javax.servlet.http.HttpServletRequest;

import static by.radomskaya.project.constant.PageConstant.ADMIN_AUTHORS_PAGE;

public class DeleteAuthorCommand implements Command {
    private AdminLogic adminLogic;

    public DeleteAuthorCommand(AdminLogic adminLogic) {
        this.adminLogic = adminLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Author author = new Author();

        try {
            if (adminLogic.deleteAuthor(author)) {
                page = ADMIN_AUTHORS_PAGE;
            }

            return page;
        } catch (DAOException e) {
            throw new CommandException(e);
        }
    }
}
