package by.radomskaya.project.command.user.order;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.PageConstant;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.OrderLogic;

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
        String page;
        List<Order> listOrders;

        try {
            int idOrder = Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID_ORDER));
            int id_user = Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID_READER));

            orderLogic.deleteOrderById(idOrder);
            listOrders = orderLogic.getPersonalOrders(id_user);

            request.setAttribute("orders", listOrders);
            request.setAttribute("messageDelete", "success");
            page = PageConstant.USER_ORDERS_CART_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
