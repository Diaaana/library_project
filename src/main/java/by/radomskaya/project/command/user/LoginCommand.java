package by.radomskaya.project.command.user;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.radomskaya.project.constant.PageConstant.ADMIN_MAIN_PAGE;
import static by.radomskaya.project.constant.PageConstant.USER_MAIN_PAGE;
import static by.radomskaya.project.constant.PageConstant.START_PAGE;

public class LoginCommand implements Command {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private UserLogic userLogic;

    public LoginCommand(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        String loginValue = request.getParameter(PARAM_LOGIN);
        String passwordValue = request.getParameter(PARAM_PASSWORD);
        HttpSession session = request.getSession(true);

        try {
            if (userLogic.checkAdmin(loginValue, passwordValue)) {
                session.setAttribute("role", "admin");
                page = ADMIN_MAIN_PAGE;
            } else if (userLogic.checkUser(loginValue, passwordValue)) {
                session.setAttribute("role", "user");
                page = USER_MAIN_PAGE;
            } else {
                page = START_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
