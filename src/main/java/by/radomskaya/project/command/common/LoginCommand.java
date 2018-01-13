package by.radomskaya.project.command.common;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AdminLogic;
import by.radomskaya.project.logic.LibrarianLogic;
import by.radomskaya.project.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.radomskaya.project.constant.PageConstant.*;

public class LoginCommand implements Command {
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private UserLogic userLogic;
    private AdminLogic adminLogic;
    private LibrarianLogic librarianLogic;

    public LoginCommand(UserLogic userLogic, AdminLogic adminLogic, LibrarianLogic librarianLogic) {
        this.userLogic = userLogic;
        this.adminLogic = adminLogic;
        this.librarianLogic = librarianLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        String loginValue = request.getParameter(PARAM_LOGIN);
        String passwordValue = request.getParameter(PARAM_PASSWORD);
        HttpSession session = request.getSession(true);

        try {
            if (adminLogic.checkAdmin(loginValue, passwordValue)) {
                session.setAttribute("role", "admin");
                page = ADMIN_MAIN_PAGE;
            } else if (userLogic.checkUser(loginValue, passwordValue)) {
                session.setAttribute("role", "user");
                page = USER_MAIN_PAGE;
            } else if (librarianLogic.checkLibrarian(loginValue, passwordValue)) {
                session.setAttribute("role", "librarian");
                page = LIBRARIAN_MAIN_PAGE;
            } else {
                session.setAttribute("role", null);
                page = START_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
