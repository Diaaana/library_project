package by.radomskaya.project.command.common;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.MessageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.constant.PropertyKeys;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.mail.generator.PasswordGenerator;
import by.radomskaya.project.logic.ReaderLogic;
import by.radomskaya.project.mail.sender.MailSender;
import by.radomskaya.project.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ForgetPasswordCommand implements Command {
    private ReaderLogic readerLogic;

    public ForgetPasswordCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page;

        try {
            int numberTicket = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_NUMBER_TICKET));
            String mail = request.getParameter(ParameterConstants.PARAM_MAIL);
            String password = PasswordGenerator.generatePassword();

            if (readerLogic.checkMailAndTicket(mail, numberTicket)) {
                readerLogic.forgotPassword(numberTicket, password);

                MailSender.sendMail(ParameterConstants.PARAM_TITTLE_MAIL, ParameterConstants.PARAM_NEW_PASSWORD_MAIL + password, mail);
                router.setRoute(Router.RouteType.REDIRECT);
                page = JspPageConstants.START_PAGE;
            } else {
                request.setAttribute(MessageConstants.MESSAGE_ERROR_FORGOT_PASSWORD, MessageManager.getLocale(locale).getMessage(PropertyKeys.FORGOT_PASSWORD_ERROR_MESSAGE));
                router.setRoute(Router.RouteType.FORWARD);
                page = JspPageConstants.FORGOT_PASSWORD_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }


        router.setPagePath(page);
        return router;
    }
}
