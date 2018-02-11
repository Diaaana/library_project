package by.radomskaya.project.command.user.account;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPage;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.ReaderLogic;

import javax.servlet.http.HttpServletRequest;

public class EditAccountCommand implements Command {
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
            int numberTicket = Integer.parseInt(request.getParameter(RequestParameter.PARAM_NUMBER_TICKET));
            user = readerLogic.getReaderByTicket(numberTicket);

            request.setAttribute("reader", user);
            page = JspPage.USER_EDIT_ACCOUNT_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
