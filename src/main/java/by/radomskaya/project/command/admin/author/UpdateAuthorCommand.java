package by.radomskaya.project.command.admin.author;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AuthorLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.ADMIN_AUTHORS_PAGE;

public class UpdateAuthorCommand implements Command {
    private final String PARAM_ID_AUTHOR = "id_author";
    private final static String PARAM_AUTHOR_SURNAME = "surname";
    private final static String PARAM_AUTHOR_NAME = "name";
    private final static String PARAM_AUTHOR_MIDDLE_NAME = "middle_name";
    private final static String PARAM_AUTHOR_COUNTRY = "country";
    private AuthorLogic authorLogic;

    public UpdateAuthorCommand(AuthorLogic authorLogic) {
        this.authorLogic = authorLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Author author = new Author();
        List<Author> listAuthors;

        author.setId(Integer.parseInt(request.getParameter(PARAM_ID_AUTHOR)));
        author.setSurname(request.getParameter(PARAM_AUTHOR_SURNAME));
        author.setName(request.getParameter(PARAM_AUTHOR_NAME));
        author.setMiddleName(request.getParameter(PARAM_AUTHOR_MIDDLE_NAME));
        author.setCountryBirth(request.getParameter(PARAM_AUTHOR_COUNTRY));

        try {
            if (authorLogic.updateAuthor(author)) {
                //success message
                listAuthors = authorLogic.getAuthors();
                request.setAttribute("authors", listAuthors);
                page = ADMIN_AUTHORS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
