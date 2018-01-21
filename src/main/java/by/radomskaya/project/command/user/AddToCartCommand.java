package by.radomskaya.project.command.user;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.OrderLogic;

import javax.servlet.http.HttpServletRequest;

import static by.radomskaya.project.constant.PageConstant.USER_BOOKS_PAGE;

public class AddToCartCommand implements Command {
    private final String PARAM_NUMBER_TICKET = "number_ticket";
    private final String PARAM_ID_BOOK = "id_book";
    private final String PARAM_ID_AUTHOR = "id_author";
    private OrderLogic orderLogic;

    public AddToCartCommand(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        int idBook = Integer.parseInt(request.getParameter(PARAM_ID_BOOK));
        int idAuthor = Integer.parseInt(request.getParameter(PARAM_ID_AUTHOR));
        Order order = new Order();
        Book book;
        Author author;

        try {
            order.setNumberTicket(Integer.parseInt(request.getParameter(PARAM_NUMBER_TICKET)));
            book = new Book(idBook);
            author = new Author(idAuthor);

            orderLogic.addToCart(order, book, author);

            page = USER_BOOKS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
