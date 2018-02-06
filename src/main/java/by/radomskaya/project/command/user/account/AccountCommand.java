package by.radomskaya.project.command.user.account;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.ReaderLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AccountCommand implements Command {
    private ReaderLogic readerLogic;

    public AccountCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        Router router = new Router();
        String page;
        User user;

        try {
            int numberTicket = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_NUMBER_TICKET));
            user = readerLogic.getReaderByTicket(numberTicket);

            session.setAttribute("userData", user);
            page = JspPageConstants.USER_ACCOUNT_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
