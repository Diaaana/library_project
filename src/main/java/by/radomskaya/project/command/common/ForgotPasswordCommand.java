package by.radomskaya.project.command.common;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.PageConstant;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.ReaderLogic;

import javax.servlet.http.HttpServletRequest;

public class ForgotPasswordCommand implements Command {
    private ReaderLogic readerLogic;

    public ForgotPasswordCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;

        try {
            int numberTicket = Integer.parseInt(request.getParameter(RequestParameter.PARAM_NUMBER_TICKET));
            String login = request.getParameter(RequestParameter.PARAM_LOGIN);
            String newPassword = request.getParameter(RequestParameter.PARAM_NEW_PASSWORD);
            String repeatNewPassword = request.getParameter(RequestParameter.PARAM_REPEAT_NEW_PASSWORD);
            if (repeatNewPassword.equals(newPassword)) {
                if (readerLogic.checkLoginAndTicket(login, numberTicket)) {
                    readerLogic.forgotPassword(numberTicket, login, newPassword);
                    request.setAttribute("messageChangePassword", "success");
                    page = PageConstant.START_PAGE;
                } else {
                    request.setAttribute("messageChangePassword", "error");
                    page = PageConstant.FORGOT_PASSWORD_PAGE;
                }
            } else {
                request.setAttribute("messageRepeatPassword", "error");
                page = PageConstant.FORGOT_PASSWORD_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }


        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
