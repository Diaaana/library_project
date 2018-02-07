package by.radomskaya.project.command.common;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.*;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.ReaderLogic;
import by.radomskaya.project.manager.MessageManager;

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
        String loginValue = request.getParameter(ParameterConstants.PARAM_LOGIN);
        String passwordValue = request.getParameter(ParameterConstants.PARAM_PASSWORD);
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
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page;
        switch (roleType) {
            case RoleType.ADMIN:
                session.setAttribute(ParameterConstants.PARAM_ROLE, RoleType.ROLE_ADMIN);
                request.setAttribute(ParameterConstants.PARAM_ADMIN_LOGIN, user.getLogin());
                page = JspPageConstants.ADMIN_MAIN_PAGE;
                break;
            case RoleType.LIBRARIAN:
                session.setAttribute(ParameterConstants.PARAM_ROLE, RoleType.ROLE_LIBRARIAN);
                request.setAttribute(ParameterConstants.PARAM_LIBRARIAN_LOGIN, user.getLogin());
                page = JspPageConstants.LIBRARIAN_MAIN_PAGE;
                break;
            case RoleType.READER:
                session.setAttribute(ParameterConstants.PARAM_ROLE, RoleType.ROLE_READER);
                session.setAttribute(RoleType.ROLE_READER, user);
                page = JspPageConstants.USER_MAIN_PAGE;
                break;
            default:
                session.setAttribute(ParameterConstants.PARAM_ROLE, RoleType.ROLE_GUEST);
                request.setAttribute(MessageConstants.MESSAGE_ERROR_LOGIN, MessageManager.getLocale(locale).getMessage(PropertyKeys.LOGIN_ERROR_MESSAGE));
                page = JspPageConstants.LOGIN_PAGE;
                break;
        }

        return page;
    }
}
