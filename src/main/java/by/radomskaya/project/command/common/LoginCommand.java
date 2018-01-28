package by.radomskaya.project.command.common;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AdminLogic;
import by.radomskaya.project.logic.LibrarianLogic;
import by.radomskaya.project.logic.ReaderLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.radomskaya.project.constant.PageConstant.*;

public class LoginCommand implements Command {
    private final String PARAM_LOGIN = "login";
    private final String PARAM_PASSWORD = "password";
    private ReaderLogic readerLogic;
    private AdminLogic adminLogic;
    private LibrarianLogic librarianLogic;

    public LoginCommand(ReaderLogic readerLogic, AdminLogic adminLogic, LibrarianLogic librarianLogic) {
        this.readerLogic = readerLogic;
        this.adminLogic = adminLogic;
        this.librarianLogic = librarianLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        int numberTicket;
        String loginValue = request.getParameter(PARAM_LOGIN);
        String passwordValue = request.getParameter(PARAM_PASSWORD);
        User user = new User();
        HttpSession session = request.getSession(true);

        try {
            if (adminLogic.checkAdmin(loginValue, passwordValue)) {
                session.setAttribute("role", "admin");
                request.setAttribute("adminLogin", loginValue);
                page = ADMIN_MAIN_PAGE;
            } else if (readerLogic.checkReader(loginValue, passwordValue)) {
                session.setAttribute("role", "reader");

                numberTicket = readerLogic.getNumberTicket(loginValue, passwordValue);
                user.setLogin(loginValue);
                user.setPassword(passwordValue);
                user.setNumberTicket(numberTicket);
                session.setAttribute("reader", user);
                page = USER_MAIN_PAGE;
            } else if (librarianLogic.checkLibrarian(loginValue, passwordValue)) {
                session.setAttribute("role", "librarian");
                request.setAttribute("librarianLogin", loginValue);
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
