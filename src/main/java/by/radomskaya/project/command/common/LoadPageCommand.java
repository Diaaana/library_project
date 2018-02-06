package by.radomskaya.project.command.common;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class LoadPageCommand implements Command {

    public LoadPageCommand() { }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        router.setPagePath(request.getParameter(ParameterConstants.PARAM_PAGE));
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
