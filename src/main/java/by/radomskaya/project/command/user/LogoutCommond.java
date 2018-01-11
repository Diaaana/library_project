package by.radomskaya.project.command.user;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.radomskaya.project.constant.PageConstant.START_PAGE;

public class LogoutCommond implements Command {
    private String page;

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        session.setAttribute("role", null);
        page = START_PAGE;
        return page;
    }
}
