package by.radomskaya.project.command.user.order;

import by.radomskaya.project.command.Command;
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

import static by.radomskaya.project.constant.JspPageConstants.USER_APPROVED_ORDERS;
import static by.radomskaya.project.constant.JspPageConstants.USER_ORDERS_CART_PAGE;

public class GetApprovedOrdersCommand implements Command {
    private OrderLogic orderLogic;

    public GetApprovedOrdersCommand(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page;
        List<Order> listOrders;

        try {
            int idUser = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_READER));
            listOrders = orderLogic.getApprovedOrders(idUser);

            if (listOrders.isEmpty()) {
                request.setAttribute(MessageConstants.MESSAGE_EMTPY_APPROVED_ORDERS, MessageManager.getLocale(locale).getMessage(PropertyKeys.EMPTY_APPROVED_ORDERS_MESSAGE));
                page = USER_ORDERS_CART_PAGE;
            } else {
                request.setAttribute("approvedOrders", listOrders);
                page = USER_APPROVED_ORDERS;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
