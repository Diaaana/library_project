package by.radomskaya.project.command.user;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AdminLogic;
import by.radomskaya.project.logic.LibrarianLogic;
import by.radomskaya.project.logic.ReaderLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;

import static by.radomskaya.project.constant.PageConstant.*;

public class RegistrationCommand implements Command {
    private final String PARAM_ADMIN = "admin";
    private final String PARAM_LIBRARIAN = "librarian";
    private final String PARAM_READER = "reader";
    private final String PARAM_ROLE = "role";
    private final String PARAM_SURNAME = "surname";
    private final String PARAM_NAME = "name";
    private final String PARAM_MIDDLE_NAME = "middle_name";
    private final String PARAM_AGE = "age";
    private final String PARAM_PHONE = "phone";
    private final String PARAM_MAIL = "mail";
    private final String PARAM_LOGIN = "login";
    private final String PARAM_PASSWORD = "password";
    private final String PARAM_PROFILE_PHOTO = "profile_photo";
    private AdminLogic adminLogic;
    private ReaderLogic registrationLogic;
    private LibrarianLogic librarianLogic;

    public RegistrationCommand(AdminLogic adminLogic, ReaderLogic registrationLogic, LibrarianLogic librarianLogic) {
        this.adminLogic = adminLogic;
        this.registrationLogic = registrationLogic;
        this.librarianLogic = librarianLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        String page = null;
        User user = new User();
        String roleUser = request.getParameter(PARAM_ROLE);

        try {
            user.setSurname(request.getParameter(PARAM_SURNAME));
            user.setName(request.getParameter(PARAM_NAME));
            user.setMiddleName(request.getParameter(PARAM_MIDDLE_NAME));
            user.setAge(Integer.parseInt(request.getParameter(PARAM_AGE)));
            user.setPhoneNumber(request.getParameter(PARAM_PHONE));
            user.setMail(request.getParameter(PARAM_MAIL));
            user.setLogin(request.getParameter(PARAM_LOGIN));
            user.setPassword(request.getParameter(PARAM_PASSWORD));
            Part filePart = request.getPart(PARAM_PROFILE_PHOTO);
            String imageName = getImageName(filePart);
            user.setProfilePhoto(imageName);

            if (roleUser.equals(PARAM_READER)) {
                if (registrationLogic.registrationReader(user)) {
                    session.setAttribute("role", "reader");
                    page = USER_MAIN_PAGE;
                } else {
                    page = REGISTRATION_PAGE;
                }
            } else if (roleUser.equals(PARAM_LIBRARIAN)) {
                if (librarianLogic.addLibrarian(user)) {
                    session.setAttribute("role", "librarian");
                    page = LIBRARIAN_MAIN_PAGE;
                } else {
                    page = REGISTRATION_PAGE;
                }
            } else if (roleUser.equals(PARAM_ADMIN)) {
                if (adminLogic.addAdmin(user)) {
                    session.setAttribute("role", "admin");
                    page = ADMIN_MAIN_PAGE;
                } else {
                    page = REGISTRATION_PAGE;
                }
            }

        } catch (ServletException | DAOException | IOException e) {
            throw new CommandException(e);
        }

        return page;
    }

    private String getImageName(Part filePart) {
        String name = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        return name;
    }
}
