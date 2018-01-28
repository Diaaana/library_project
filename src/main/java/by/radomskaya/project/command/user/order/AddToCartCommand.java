package by.radomskaya.project.command.user.order;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.BookLogic;
import by.radomskaya.project.logic.OrderLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.USER_BOOKS_PAGE;

public class AddToCartCommand implements Command {
    private final String PARAM_NUMBER_TICKET = "number_ticket";
    private final String PARAM_ID_BOOK = "id_book";
    private final String PARAM_ID_AUTHOR = "id_author";
    private OrderLogic orderLogic;
    private BookLogic bookLogic;

    public AddToCartCommand(OrderLogic orderLogic, BookLogic bookLogic) {
        this.orderLogic = orderLogic;
        this.bookLogic = bookLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        int idBook = Integer.parseInt(request.getParameter(PARAM_ID_BOOK));
        int idAuthor = Integer.parseInt(request.getParameter(PARAM_ID_AUTHOR));
        Order order = new Order();
        Book book;
        Author author;
        List<Book> listBooks;

        try {
            order.setNumberTicket(Integer.parseInt(request.getParameter(PARAM_NUMBER_TICKET)));
            book = new Book(idBook);
            author = new Author(idAuthor);

            orderLogic.addToCart(order, book, author);

            listBooks = bookLogic.getBooks();
            request.setAttribute("books", listBooks);
            page = USER_BOOKS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
