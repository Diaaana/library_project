package by.radomskaya.project.command.user;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Reader;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;

import static by.radomskaya.project.constant.PageConstant.REGISTRATION_PAGE;
import static by.radomskaya.project.constant.PageConstant.START_PAGE;

public class RegistrationCommand implements Command {
    private static final String PARAM_SURNAME = "surname";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_MIDDLE_NAME = "middleName";
    private static final String PARAM_AGE = "age";
    private static final String PARAM_PHONE = "phone";
    private static final String PARAM_MAIL = "mail";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private UserLogic registrationLogic;

    public RegistrationCommand(UserLogic registrationLogic) {
        this.registrationLogic = registrationLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Reader reader = new Reader();
        String page;
        reader.setSurname(request.getParameter(PARAM_SURNAME));
        reader.setName(request.getParameter(PARAM_NAME));
        reader.setMiddleName(request.getParameter(PARAM_MIDDLE_NAME));
        reader.setAge(Integer.parseInt(request.getParameter(PARAM_AGE)));
        reader.setPhoneNumber(request.getParameter(PARAM_PHONE));
        reader.setMail(request.getParameter(PARAM_MAIL));
        reader.setLogin(request.getParameter(PARAM_LOGIN));
        reader.setPassword(request.getParameter(PARAM_PASSWORD));

        try {
            if (registrationLogic.registrationUser(reader)) {
                page = START_PAGE;
            } else {
                page = REGISTRATION_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
