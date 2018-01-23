package by.radomskaya.project.command.user.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.USER_BOOKS_PAGE;

public class FindBookByTittleCommand implements Command {
    private final String PARAM_BOOK = "book";
    private BookLogic bookLogic;

    public FindBookByTittleCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String tittle = request.getParameter(PARAM_BOOK);
        List<Book> listFoundBooksByTittle;

        try {
            if (bookLogic.findBooksByTittle(tittle)) {
                listFoundBooksByTittle = bookLogic.getFoundBooksByTittle(tittle);
                request.setAttribute("foundBooksByTittle", listFoundBooksByTittle);
                page = USER_BOOKS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
