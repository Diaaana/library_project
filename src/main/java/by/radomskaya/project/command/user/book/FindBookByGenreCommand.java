package by.radomskaya.project.command.user.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.PageConstant;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindBookByGenreCommand implements Command {
    private BookLogic bookLogic;

    public FindBookByGenreCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        List<Book> listFoundBooksByGenre;

        try {
            String genre = request.getParameter(RequestParameter.PARAM_GENRE);
            listFoundBooksByGenre = bookLogic.findBooksByGenre(genre);

            if (listFoundBooksByGenre.isEmpty()) {
                request.setAttribute("messageFindBook", "empty");
                page = PageConstant.USER_FIND_BOOKS_PAGE;
            } else {
                request.setAttribute("foundBooks", listFoundBooksByGenre);
                page = PageConstant.USER_FIND_BOOKS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
