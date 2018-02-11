package by.radomskaya.project.command.user;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPage;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.constant.RoleType;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.LibrarianLogic;
import by.radomskaya.project.logic.ReaderLogic;
import by.radomskaya.project.validation.InputParamValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;

public class RegistrationCommand implements Command {
    private ReaderLogic readerLogic;
    private LibrarianLogic librarianLogic;

    public RegistrationCommand(ReaderLogic readerLogic, LibrarianLogic librarianLogic) {
        this.readerLogic = readerLogic;
        this.librarianLogic = librarianLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        User user;
        String login = request.getParameter(RequestParameter.PARAM_LOGIN);
        String roleUser = request.getParameter(RequestParameter.PARAM_ROLE);
        System.out.println(roleUser);

        try {
            user = setUserFromRequest(request);
            System.out.println(user);
            if (!readerLogic.checkLogin(login)) {
                switch (roleUser) {
                    case RoleType.LIBRARIAN:
                        librarianLogic.addLibrarian(user);
                        request.setAttribute("messageRegLibrarian", "success");
                        page = JspPage.START_PAGE;
                        break;
                    case RoleType.READER:
                        readerLogic.registrationReader(user);
                        request.setAttribute("messageRegUser", "success");
                        page = JspPage.START_PAGE;
                        break;
                }
            } else {
                request.setAttribute("messageSameLogin", "true");
                page = JspPage.REGISTRATION_PAGE;
            }

        } catch (ServletException | DAOException | IOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.REDIRECT);

        return router;
    }

    private User setUserFromRequest(HttpServletRequest request) throws IOException, ServletException {
        User user = new User();
        int numberTicket = generateNumberTicket();
        String surname = request.getParameter(RequestParameter.PARAM_SURNAME);
        String name = request.getParameter(RequestParameter.PARAM_NAME);
        String middleName = request.getParameter(RequestParameter.PARAM_MIDDLE_NAME);
        int age = Integer.parseInt(request.getParameter(RequestParameter.PARAM_AGE));
        String phoneNumber = request.getParameter(RequestParameter.PARAM_PHONE);
        String mail = request.getParameter(RequestParameter.PARAM_MAIL);
        String login = request.getParameter(RequestParameter.PARAM_LOGIN);
        String password = request.getParameter(RequestParameter.PARAM_PASSWORD);
        Part filePart = request.getPart(RequestParameter.PARAM_PROFILE_PHOTO);
        String imageName = getImageName(filePart);

        if (InputParamValidator.isValidateUserData(surname, name, middleName, age, phoneNumber, mail, login, password)) {
            user = new User(numberTicket, surname, name, middleName, age, phoneNumber, mail, login, password, imageName);
        }
        return user;
    }

    private String getImageName(Part filePart) {
        String name = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        return name;
    }

    private int generateNumberTicket() {
        int numberTicket = 1000 + (int)(Math.random() * 10000);
        return numberTicket;
    }
}
