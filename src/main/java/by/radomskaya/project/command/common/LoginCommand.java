package by.radomskaya.project.command.common;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPage;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.constant.RoleType;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.ReaderLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {
    private ReaderLogic readerLogic;

    public LoginCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page;
        String roleType;
        String loginValue = request.getParameter(RequestParameter.PARAM_LOGIN);
        String passwordValue = request.getParameter(RequestParameter.PARAM_PASSWORD);
        User user;

        try {
            roleType = readerLogic.checkLoginPassword(loginValue, passwordValue);
            user = readerLogic.getUserByLoginPassword(loginValue, passwordValue);
            page = setRole(request, roleType, user);
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }

    private String setRole(HttpServletRequest request, String roleType, User user) {
        HttpSession session = request.getSession();
        String page = null;
        switch (roleType) {
            case RoleType.ADMIN:
                session.setAttribute("role", "admin");
                request.setAttribute("adminLogin", user.getLogin());
                page = JspPage.ADMIN_MAIN_PAGE;
                break;
            case RoleType.LIBRARIAN:
                session.setAttribute("role", "librarian");
                request.setAttribute("librarianLogin", user.getLogin());
                page = JspPage.LIBRARIAN_MAIN_PAGE;
                break;
            case RoleType.READER:
                session.setAttribute("role", "reader");
                session.setAttribute("reader", user);
                page = JspPage.USER_MAIN_PAGE;
                break;
            default:
                session.setAttribute("role", null);
                request.setAttribute("messageLogin", "error");
                page = JspPage.START_PAGE;
                break;
        }

        return page;
    }
}
