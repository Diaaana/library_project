package by.radomskaya.project.command.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.OrderLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.LIBRARIAN_ORDERS_PAGE;

public class ShowOrdersCommand implements Command {
    private OrderLogic orderLogic;

    public ShowOrdersCommand(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        List<Order> listOrders = new ArrayList<>();

        try {
            listOrders = orderLogic.getAllOrders();
            request.setAttribute("orders", listOrders);
            page = LIBRARIAN_ORDERS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
