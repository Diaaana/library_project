package by.radomskaya.project.command.user.order;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.OrderLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.USER_ORDERS_CART_PAGE;

public class DeleteOrderCommand implements Command {
    private final static String PARAM_ID_ORDER = "id_order";
    private final static String PARAM_NUMBER_TICKET = "number_ticket";
    private OrderLogic orderLogic;

    public DeleteOrderCommand(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        List<Order> listOrders;
        int idOrder = Integer.parseInt(request.getParameter(PARAM_ID_ORDER));
        int numberTicket = Integer.parseInt(request.getParameter(PARAM_NUMBER_TICKET));

        try {
            orderLogic.deleteOrderById(idOrder);
            listOrders = orderLogic.getPersonalOrders(numberTicket);
            request.setAttribute("orders", listOrders);
            page = USER_ORDERS_CART_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
