package by.radomskaya.project.command.user;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;

import static by.radomskaya.project.constant.PageConstant.MAIN_PAGE;
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
        String page = null;
        String loginValue = request.getParameter(PARAM_LOGIN);
        String passwordValue = request.getParameter(PARAM_PASSWORD);

        try {
            if (userLogic.checkUser(loginValue, passwordValue)) {
               // request.setAttribute("user", loginValue);
                page = MAIN_PAGE;
            } else {
                page = START_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
