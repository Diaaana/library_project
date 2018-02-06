package by.radomskaya.project.command.user.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetBooksCommand implements Command {
    private BookLogic bookLogic;

    public GetBooksCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router = new Router();
        String page;
        List<Book> listBooks;
        int bookPage = 1;

        if (request.getParameter("page") != null) {
            bookPage = Integer.parseInt(request.getParameter("page"));
        }

        try {
            //listBooks = bookLogic.getBooks();
            listBooks = bookLogic.getBooksWithPages(bookPage);
            request.setAttribute("noOfPages", bookLogic.getNoOfPages());
            session.setAttribute("books", listBooks);
            session.setAttribute("currentPage", bookPage);
            page = JspPageConstants.USER_BOOKS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
