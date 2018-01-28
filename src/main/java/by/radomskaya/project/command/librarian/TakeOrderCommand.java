package by.radomskaya.project.command.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.OrderLogic;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.LIBRARIAN_ORDERS_PAGE;

public class TakeOrderCommand implements Command {
    private final String PARAM_ID_ORDER = "id_order";
    private final String PARAM_ID_BOOK = "id_book";
    private final String PARAM_NUMBER_TICKET = "number_ticket";
    private final String PARAM_DATE_BORROW = "date_borrow";
    private final String PARAM_DATE_RETURN = "date_return";
    private final String PARAM_METHOD_BORROW = "method_borrow";
    private OrderLogic orderLogic;

    public TakeOrderCommand(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Order order = new Order();
        Book book = new Book();
        List<Order> listOrders;

        int idOrder = Integer.parseInt(request.getParameter(PARAM_ID_ORDER));
        int numberTicket = Integer.parseInt(request.getParameter(PARAM_NUMBER_TICKET));
        int idBook = Integer.parseInt(request.getParameter(PARAM_ID_BOOK));
        Date dateBorrow= Date.valueOf(request.getParameter(PARAM_DATE_BORROW));
        Date dateReturn = Date.valueOf(request.getParameter(PARAM_DATE_RETURN));
        String methodBorrow = request.getParameter(PARAM_METHOD_BORROW);

        System.out.println(idOrder);


        order.setNumberTicket(numberTicket);
        book.setId(idBook);
        order.setBook(book);
        order.setDateBorrow(dateBorrow);
        order.setDateReturn(dateReturn);
        order.setMethodBorrow(methodBorrow);

        try {
            orderLogic.takeOrder(order);
            orderLogic.deleteOrderById(idOrder);
            listOrders = orderLogic.getAllOrders();
            request.setAttribute("orders", listOrders);
            page = LIBRARIAN_ORDERS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
