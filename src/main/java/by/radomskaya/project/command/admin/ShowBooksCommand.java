package by.radomskaya.project.command.admin;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AdminLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.ADMIN_SHOW_BOOKS_PAGE;

public class ShowBooksCommand implements Command {
    private AdminLogic adminLogic;

    public ShowBooksCommand(AdminLogic adminLogic) {
        this.adminLogic = adminLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        List<Book> listBooks;

        try {
            listBooks = adminLogic.getBooks();
            request.setAttribute("books", listBooks);
            request.setAttribute("success", "Все хорошо");
            page = ADMIN_SHOW_BOOKS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
