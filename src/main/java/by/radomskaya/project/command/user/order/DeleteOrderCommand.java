package by.radomskaya.project.command.user.order;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.LogicException;
import by.radomskaya.project.logic.OrderLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteOrderCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteOrderCommand.class);
    private OrderLogic orderLogic;

    public DeleteOrderCommand(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        List<Order> listOrders;

        try {
            int idOrder = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_ORDER));
            int id_user = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_READER));

            orderLogic.deleteOrderById(idOrder);
            listOrders = orderLogic.getPersonalOrders(id_user);

            request.setAttribute(ParameterConstants.PARAM_ORDERS, listOrders);
            page = JspPageConstants.USER_ORDERS_CART_PAGE;

        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
