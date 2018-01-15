package by.radomskaya.project.command.admin;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Reader;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AdminLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.ADMIN_READERS_PAGE;

public class ShowReadersCommand implements Command {
    private AdminLogic adminLogic;

    public ShowReadersCommand(AdminLogic adminLogic) {
        this.adminLogic = adminLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        List<Reader> listReader;

        try {
            listReader = adminLogic.getReaders();
            request.setAttribute("readers", listReader);
            page = ADMIN_READERS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}