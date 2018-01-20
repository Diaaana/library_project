package by.radomskaya.project.command.admin;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AuthorLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.ADMIN_AUTHORS_PAGE;

public class ShowAuthorsCommand implements Command {
    private AuthorLogic authorLogic;

    public ShowAuthorsCommand(AuthorLogic authorLogic) {
        this.authorLogic = authorLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        List<Author> listAuthors;

        try {
            listAuthors = authorLogic.getAuthors();
            System.out.println(listAuthors);
            request.setAttribute("authors", listAuthors);
            page = ADMIN_AUTHORS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
