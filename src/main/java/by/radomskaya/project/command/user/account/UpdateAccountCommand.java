package by.radomskaya.project.command.user.account;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.ReaderLogic;
import by.radomskaya.project.validation.InputParamValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;

public class UpdateAccountCommand implements Command {
    private ReaderLogic readerLogic;

    public UpdateAccountCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String page = null;
        User user;

        try {
            user = setUserFromRequest(request);

            if (readerLogic.updateUser(user)) {
                session.setAttribute("userData", user);
                page = JspPageConstants.USER_ACCOUNT_PAGE;
            }

        } catch (DAOException | ServletException | IOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.REDIRECT);
        return router;
    }

    private User setUserFromRequest(HttpServletRequest request) throws IOException, ServletException {
        User user = new User();
        int numberTicket = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_NUMBER_TICKET));
        String surname = request.getParameter(ParameterConstants.PARAM_SURNAME);
        String name = request.getParameter(ParameterConstants.PARAM_NAME);
        String middleName = request.getParameter(ParameterConstants.PARAM_MIDDLE_NAME);
        int age = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_AGE));
        String phoneNumber = request.getParameter(ParameterConstants.PARAM_PHONE);
        String mail = request.getParameter(ParameterConstants.PARAM_MAIL);
        String login = request.getParameter(ParameterConstants.PARAM_LOGIN);
        Part filePart = request.getPart(ParameterConstants.PARAM_PROFILE_PHOTO);
        String imageName = getImageName(filePart);
        if (imageName.equals(ParameterConstants.PARAM_EMPTY_PROFILE_PHOTO)) {
            user.setProfilePhoto(request.getParameter(ParameterConstants.PARAM_OLD_PROFILE_PHOTO));
        } else {
            user.setProfilePhoto(imageName);
        }

        if (InputParamValidator.isValidateAccountData(surname, name, middleName, age, phoneNumber, mail, login)) {
            user.setNumberTicket(numberTicket);
            user.setSurname(surname);
            user.setName(name);
            user.setMiddleName(middleName);
            user.setAge(age);
            user.setPhoneNumber(phoneNumber);
            user.setMail(mail);
            user.setLogin(login);
        }

        return user;
    }

    private String getImageName(Part filePart) {
        String name = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        return name;
    }
}
