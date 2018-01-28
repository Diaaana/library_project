package by.radomskaya.project.command.user.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;

import javax.servlet.http.HttpServletRequest;

import static by.radomskaya.project.constant.PageConstant.USER_PERSONAL_BOOK_PAGE;

public class GetPersonalBookCommand implements Command {
    private final String PARAM_ID_BOOK = "id_book";
    private BookLogic bookLogic;

    public GetPersonalBookCommand(BookLogic bookLogic) {
        this.bookLogic = bookLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Book book;
        int idBook = Integer.parseInt(request.getParameter(PARAM_ID_BOOK));

        try {
            book = bookLogic.getBookById(idBook);
            System.out.println(book);
            request.setAttribute("personalBook", book);
            page = USER_PERSONAL_BOOK_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
