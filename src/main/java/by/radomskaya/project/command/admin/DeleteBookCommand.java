package by.radomskaya.project.command.admin;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.ADMIN_BOOKS_PAGE;


public class DeleteBookCommand implements Command {
    private final String PARAM_ID_BOOK = "id";
    private BookLogic bookLogic;

    public DeleteBookCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        List<Book> listBooks;
        int id = Integer.parseInt(request.getParameter(PARAM_ID_BOOK));

        try {
            if (bookLogic.deleteBook(id)) {
                listBooks = bookLogic.getBooks();
                request.setAttribute("books", listBooks);
                page = ADMIN_BOOKS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
