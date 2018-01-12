package by.radomskaya.project.command.common;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String locale = request.getParameter("locale");
        session.setAttribute("locale", locale);
        return request.getParameter("url");
    }
}
