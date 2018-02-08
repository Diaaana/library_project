package by.radomskaya.project.command.common;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page;

        HttpSession session = request.getSession(false);
        session.invalidate();

        page = JspPageConstants.INDEX_PAGE;
        router.setPagePath(page);
        router.setRoute(Router.RouteType.REDIRECT);
        return router;

    }
}
