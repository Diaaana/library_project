package by.radomskaya.project.command.user.account;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.ReaderLogic;

import javax.servlet.http.HttpServletRequest;

import static by.radomskaya.project.constant.PageConstant.USER_ACCOUNT_PAGE;

public class ChangePasswordCommand implements Command {
    private final String PARAM_NUMBER_TICKET = "number_ticket";
    private final String PARAM_OLD_PASSWORD = "old_password";
    private final String PARAM_NEW_PASSWORD = "new_password";
    private final String PARAM_REPEAT_NEW_PASSWORD = "repeat_new_password";
    private ReaderLogic readerLogic;

    public ChangePasswordCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String truePassword;
        int numberTicket = Integer.parseInt(request.getParameter(PARAM_NUMBER_TICKET));
        String oldPassword = request.getParameter(PARAM_OLD_PASSWORD);
        String newPassword = request.getParameter(PARAM_NEW_PASSWORD);
        String repeatNewPassword = request.getParameter(PARAM_REPEAT_NEW_PASSWORD);

        try {
            truePassword = readerLogic.getPassword(numberTicket);
            if (oldPassword.equals(truePassword)) {
                if (repeatNewPassword.equals(newPassword)) {
                    if (readerLogic.changePassword(numberTicket, newPassword)) {
                        page = USER_ACCOUNT_PAGE;
                    }
                }
            } else {
                System.out.println("Неправильный пароль");
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
