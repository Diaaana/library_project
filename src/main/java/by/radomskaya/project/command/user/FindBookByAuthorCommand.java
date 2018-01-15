package by.radomskaya.project.command.user;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.USER_BOOKS_PAGE;

public class FindBookByAuthorCommand implements Command {
    private final String PARAM_AUTHOR = "author";
    private UserLogic userLogic;

    public FindBookByAuthorCommand(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String author = request.getParameter(PARAM_AUTHOR);
        List<Book> listFoundBooksByAuthor;

        try {
            if (userLogic.findBooksByAuthor(author)) {
                listFoundBooksByAuthor = userLogic.getFoundBooksByAuthor(author);
                request.setAttribute("foundBooksByAuthor", listFoundBooksByAuthor);
                page = USER_BOOKS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
