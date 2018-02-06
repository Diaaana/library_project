package by.radomskaya.project.command.user.account;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.MessageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.constant.PropertyKeys;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.ReaderLogic;
import by.radomskaya.project.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.radomskaya.project.constant.JspPageConstants.USER_ACCOUNT_PAGE;
import static by.radomskaya.project.constant.JspPageConstants.USER_CHANGE_PASSWORD_PAGE;

public class ChangePasswordCommand implements Command {
    private ReaderLogic readerLogic;

    public ChangePasswordCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        Router router = new Router();
        String page = null;
        String truePassword;

        int numberTicket = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_NUMBER_TICKET));
        String oldPassword = request.getParameter(ParameterConstants.PARAM_OLD_PASSWORD);
        String newPassword = request.getParameter(ParameterConstants.PARAM_NEW_PASSWORD);
        String repeatNewPassword = request.getParameter(ParameterConstants.PARAM_REPEAT_NEW_PASSWORD);

        try {
            truePassword = readerLogic.getPassword(numberTicket);
            if (!oldPassword.equals(truePassword)) {
                request.setAttribute(MessageConstants.MESSAGE_WRONG_TRUE_PASSWORD, MessageManager.getLocale(locale).getMessage(PropertyKeys.WRONG_TRUE_PASSWORD_MESSAGE));
                router.setRoute(Router.RouteType.FORWARD);
                page = USER_CHANGE_PASSWORD_PAGE;
            } else if (!repeatNewPassword.equals(newPassword)) {
                request.setAttribute(MessageConstants.MESSAGE_WRONG_REPEAT_PASSWORD, MessageManager.getLocale(locale).getMessage(PropertyKeys.WRONG_REPEAT_PASSWORD_MESSAGE));
                router.setRoute(Router.RouteType.FORWARD);
                page = USER_CHANGE_PASSWORD_PAGE;
            } else if (readerLogic.changePassword(numberTicket, newPassword)) {
                router.setRoute(Router.RouteType.REDIRECT);
                page = USER_ACCOUNT_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        return router;
    }
}
