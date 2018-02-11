package by.radomskaya.project.command.user.account;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.ReaderLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.radomskaya.project.constant.JspPage.USER_ACCOUNT_PAGE;
import static by.radomskaya.project.constant.JspPage.USER_CHANGE_PASSWORD_PAGE;

public class ChangePasswordCommand implements Command {
    private ReaderLogic readerLogic;

    public ChangePasswordCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router = new Router();
        String page = null;
        String truePassword;

        int numberTicket = Integer.parseInt(request.getParameter(RequestParameter.PARAM_NUMBER_TICKET));
        String oldPassword = request.getParameter(RequestParameter.PARAM_OLD_PASSWORD);
        String newPassword = request.getParameter(RequestParameter.PARAM_NEW_PASSWORD);
        String repeatNewPassword = request.getParameter(RequestParameter.PARAM_REPEAT_NEW_PASSWORD);

        try {
            truePassword = readerLogic.getPassword(numberTicket);
            if (!oldPassword.equals(truePassword)) {
                session.setAttribute("messageWrongTruePassword", "error");
                page = USER_CHANGE_PASSWORD_PAGE;
            } else if (!repeatNewPassword.equals(newPassword)) {
                session.setAttribute("messageWrongRepeatPassword", "error");
                page = USER_CHANGE_PASSWORD_PAGE;
            } else if (readerLogic.changePassword(numberTicket, newPassword)) {
                session.setAttribute("messageChangePassword", "success");
                page = USER_ACCOUNT_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.REDIRECT);
        return router;
    }
}
