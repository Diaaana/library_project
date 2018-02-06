package by.radomskaya.project.command.user.order;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.MessageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.constant.PropertyKeys;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.OrderLogic;
import by.radomskaya.project.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteOrderCommand implements Command {
    private OrderLogic orderLogic;

    public DeleteOrderCommand(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page;
        List<Order> listOrders;

        try {
            int idOrder = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_ORDER));
            int id_user = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_READER));

            orderLogic.deleteOrderById(idOrder);
            listOrders = orderLogic.getPersonalOrders(id_user);

            request.setAttribute("orders", listOrders);
            request.setAttribute(MessageConstants.MESSAGE_SUCCESS_DELETE_ORDER, MessageManager.getLocale(locale).getMessage(PropertyKeys.SUCCESS_DELETE_ORDER_MESSAGE));
            page = JspPageConstants.USER_ORDERS_CART_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
