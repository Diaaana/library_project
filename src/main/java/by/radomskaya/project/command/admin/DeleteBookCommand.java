package by.radomskaya.project.command.admin;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AdminLogic;

import javax.servlet.http.HttpServletRequest;

import static by.radomskaya.project.constant.PageConstant.ADMIN_BOOKS_PAGE;

public class DeleteBookCommand implements Command {
    private AdminLogic adminLogic;

    public DeleteBookCommand(AdminLogic adminLogic) {
        this.adminLogic = adminLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Book book = new Book();
        try {
            if (adminLogic.deleteBook(book)) {
                page = ADMIN_BOOKS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
