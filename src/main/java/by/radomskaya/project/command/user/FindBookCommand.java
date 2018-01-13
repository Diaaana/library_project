package by.radomskaya.project.command.user;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.UserLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.USER_BOOKS_PAGE;

public class FindBookCommand implements Command {
    private final static String PARAM_BOOK = "book";
    private UserLogic userLogic;

    public FindBookCommand(UserLogic userLogic) {
        this.userLogic = userLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String tittle = request.getParameter(PARAM_BOOK);
        List<Book> listBook;

        try {
            if (userLogic.findBook(tittle)) {
                listBook = userLogic.getFoundBook(tittle);
                request.setAttribute("book", listBook);
                page = USER_BOOKS_PAGE;
            }

        } catch (DAOException e) {
            e.printStackTrace();
        }
        return page;
    }
}
