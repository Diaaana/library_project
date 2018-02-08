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

public class EditAccountCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(EditAccountCommand.class);
    private ReaderLogic readerLogic;

    public EditAccountCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        User user;

        try {
            int numberTicket = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_NUMBER_TICKET));
            user = readerLogic.getReaderByTicket(numberTicket);

            request.setAttribute(ParameterConstants.PARAM_READER, user);
            page = JspPageConstants.USER_EDIT_ACCOUNT_PAGE;
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
