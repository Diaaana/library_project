package by.radomskaya.project.command.admin.author;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AuthorLogic;

import javax.servlet.http.HttpServletRequest;

import static by.radomskaya.project.constant.PageConstant.ADMIN_EDIT_AUTHOR_PAGE;

public class EditAuthorCommand implements Command {
    private final String PARAM_ID_AUTHOR = "id_author";
    private AuthorLogic authorLogic;

    public EditAuthorCommand(AuthorLogic authorLogic) {
        this.authorLogic = authorLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Author author;
        int idAuthor = Integer.parseInt(request.getParameter(PARAM_ID_AUTHOR));

        try {
            author = authorLogic.getAuthorById(idAuthor);
            request.setAttribute("author", author);
            page = ADMIN_EDIT_AUTHOR_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
