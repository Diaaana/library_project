package by.radomskaya.project.command.admin.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPage;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteBookCommand implements Command {
    private BookLogic bookLogic;

    public DeleteBookCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        List<Book> listBooks;

        try {
            int idBook = Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID_BOOK));

            if (bookLogic.deleteBook(idBook)) {
                listBooks = bookLogic.getBooks();
                request.setAttribute("books", listBooks);
                request.setAttribute("messageDelete", "success");
                page = JspPage.ADMIN_BOOKS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
