package by.radomskaya.project.command.user.account;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.LogicException;
import by.radomskaya.project.logic.ReaderLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AccountCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AccountCommand.class);
    private ReaderLogic readerLogic;

    public AccountCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        Router router = new Router();
        String page = null;
        User user;

        try {
            int numberTicket = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_NUMBER_TICKET));
            user = readerLogic.getReaderByTicket(numberTicket);

            session.setAttribute(ParameterConstants.PARAM_USER_DATA, user);
            page = JspPageConstants.USER_ACCOUNT_PAGE;
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
