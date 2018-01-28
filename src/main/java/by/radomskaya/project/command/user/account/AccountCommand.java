package by.radomskaya.project.command.user.account;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.ReaderLogic;

import javax.servlet.http.HttpServletRequest;

import static by.radomskaya.project.constant.PageConstant.USER_ACCOUNT_PAGE;

public class AccountCommand implements Command {
    private final String PARAM_NUMBER_TICKET = "number_ticket";
    private ReaderLogic readerLogic;

    public AccountCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        int numberTicket = Integer.parseInt(request.getParameter(PARAM_NUMBER_TICKET));
        User user;

        try {
            user = readerLogic.getReaderByTicket(numberTicket);
            request.setAttribute("user", user);
            page = USER_ACCOUNT_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
