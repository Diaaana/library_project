package by.radomskaya.project.command.admin;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Reader;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AdminLogic;

import javax.servlet.http.HttpServletRequest;

import static by.radomskaya.project.constant.PageConstant.ADMIN_READERS_PAGE;

public class DeleteReaderCommand implements Command {
    private AdminLogic adminLogic;

    public DeleteReaderCommand(AdminLogic adminLogic) {
        this.adminLogic = adminLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Reader reader = new Reader();
        try {
            if (adminLogic.deleteReader(reader)) {
                page = ADMIN_READERS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
