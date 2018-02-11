package by.radomskaya.project.command.user.account;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPage;
import by.radomskaya.project.constant.RequestParameter;
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
                page = JspPage.USER_ACCOUNT_PAGE;
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
        int numberTicket = Integer.parseInt(request.getParameter(RequestParameter.PARAM_NUMBER_TICKET));
        String surname = request.getParameter(RequestParameter.PARAM_SURNAME);
        String name = request.getParameter(RequestParameter.PARAM_NAME);
        String middleName = request.getParameter(RequestParameter.PARAM_MIDDLE_NAME);
        int age = Integer.parseInt(request.getParameter(RequestParameter.PARAM_AGE));
        String phoneNumber = request.getParameter(RequestParameter.PARAM_PHONE);
        String mail = request.getParameter(RequestParameter.PARAM_MAIL);
        String login = request.getParameter(RequestParameter.PARAM_LOGIN);
        Part filePart = request.getPart(RequestParameter.PARAM_PROFILE_PHOTO);
        String imageName = getImageName(filePart);
        if (imageName.equals(RequestParameter.PARAM_EMPTY_PROFILE_PHOTO)) {
            user.setProfilePhoto(request.getParameter(RequestParameter.PARAM_OLD_PROFILE_PHOTO));
        } else {
            user.setProfilePhoto(imageName);
        }

        if (InputParamValidator.isValidateAccountData(surname, name, middleName, age, phoneNumber, mail, login)) {
            user = new User(numberTicket, surname, name, middleName, age, phoneNumber, mail, login);
        }

        return user;
    }

    private String getImageName(Part filePart) {
        String name = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        return name;
    }
}
