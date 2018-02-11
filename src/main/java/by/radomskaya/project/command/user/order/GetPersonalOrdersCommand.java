package by.radomskaya.project.command.user.order;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPage;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.OrderLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GetPersonalOrdersCommand implements Command {
    private OrderLogic orderLogic;

    public GetPersonalOrdersCommand(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        List<Order> listOrders;

        try {
            int idUser = Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID_READER));

            if (orderLogic.checkPersonalOrders(idUser)) {
                listOrders = orderLogic.getPersonalOrders(idUser);
                request.setAttribute("orders", listOrders);
                page = JspPage.USER_ORDERS_CART_PAGE;
            } else {
                request.setAttribute("messageOrders", "empty");
                page = JspPage.USER_ORDERS_CART_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
