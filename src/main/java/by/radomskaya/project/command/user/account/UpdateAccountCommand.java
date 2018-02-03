package by.radomskaya.project.command.user.account;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.PageConstant;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.ReaderLogic;

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
                page = PageConstant.USER_ACCOUNT_PAGE;
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
        user.setNumberTicket(Integer.parseInt(request.getParameter(RequestParameter.PARAM_NUMBER_TICKET)));
        user.setSurname(request.getParameter(RequestParameter.PARAM_SURNAME));
        user.setName(request.getParameter(RequestParameter.PARAM_NAME));
        user.setMiddleName(request.getParameter(RequestParameter.PARAM_MIDDLE_NAME));
        user.setAge(Integer.parseInt(request.getParameter(RequestParameter.PARAM_AGE)));
        user.setPhoneNumber(request.getParameter(RequestParameter.PARAM_PHONE));
        user.setMail(request.getParameter(RequestParameter.PARAM_MAIL));
        user.setLogin(request.getParameter(RequestParameter.PARAM_LOGIN));
        Part filePart = request.getPart(RequestParameter.PARAM_PROFILE_PHOTO);
        String imageName = getImageName(filePart);
        if (imageName.equals(RequestParameter.PARAM_EMPTY_PROFILE_PHOTO)) {
            user.setProfilePhoto(request.getParameter(RequestParameter.PARAM_OLD_PROFILE_PHOTO));
        } else {
            user.setProfilePhoto(imageName);
        }
        return user;
    }

    private String getImageName(Part filePart) {
        String name = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        return name;
    }
}
