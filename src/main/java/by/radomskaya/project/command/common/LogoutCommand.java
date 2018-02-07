package by.radomskaya.project.command.common;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private String page;

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession(true);
        session.removeAttribute(ParameterConstants.PARAM_ROLE);
        //session.setAttribute(ParameterConstants.PARAM_ROLE, RoleType.ROLE_GUEST);
        page = JspPageConstants.LOGIN_PAGE;
        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
