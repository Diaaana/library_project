package by.radomskaya.project.command.user;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.OrderLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.USER_ORDERS_CART_PAGE;

public class GetPersonalOrdersCommand implements Command {
    private final String PARAM_NUMBER_TICKET = "number_ticket";
    private OrderLogic orderLogic;

    public GetPersonalOrdersCommand(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        int numberTicket = Integer.parseInt(request.getParameter(PARAM_NUMBER_TICKET));
        List<Order> listOrders;

        try {
            if (orderLogic.checkPersonalOrders(numberTicket)) {
                listOrders = orderLogic.getPersonalOrders(numberTicket);
                request.setAttribute("orders", listOrders);
                page = USER_ORDERS_CART_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
