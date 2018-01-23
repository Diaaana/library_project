package by.radomskaya.project.command.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

import static by.radomskaya.project.constant.PageConstant.LIBRARIAN_TAKE_ORDER_PAGE;

public class CheckOrderCommand implements Command {
    private final String PARAM_ID_ORDER = "id_order";
    private final String PARAM_ID_BOOK = "id_book";
    private final String PARAM_NUMBER_TICKET = "number_ticket";

    public CheckOrderCommand() { }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Order order = new Order();
        Book book = new Book();

        int idOrder = Integer.parseInt(request.getParameter(PARAM_ID_ORDER));
        int numberTicket = Integer.parseInt(request.getParameter((PARAM_NUMBER_TICKET)));
        int idBook = Integer.parseInt(request.getParameter((PARAM_ID_BOOK)));

        book.setId(idBook);
        order.setId(idOrder);
        order.setNumberTicket(numberTicket);
        order.setBook(book);

        request.setAttribute("order", order);
        page = LIBRARIAN_TAKE_ORDER_PAGE;

        return page;
    }
}
