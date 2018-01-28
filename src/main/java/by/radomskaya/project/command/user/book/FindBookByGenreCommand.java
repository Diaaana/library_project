package by.radomskaya.project.command.user.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.USER_BOOKS_PAGE;

public class FindBookByGenreCommand implements Command {
    private final String PARAM_GENRE = "genre";
    private BookLogic bookLogic;

    public FindBookByGenreCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        String genre = request.getParameter(PARAM_GENRE);
        List<Book> listFoundBooksByGenre;

        try {
            listFoundBooksByGenre = bookLogic.findBooksByGenre(genre);
            request.setAttribute("foundBooksByGenre", listFoundBooksByGenre);
            page = USER_BOOKS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
