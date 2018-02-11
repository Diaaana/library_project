package by.radomskaya.project.command.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPage;
import by.radomskaya.project.constant.RequestParameter;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.OrderLogic;
import by.radomskaya.project.validation.InputParamValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

public class TakeOrderCommand implements Command {
    private OrderLogic orderLogic;

    public TakeOrderCommand(OrderLogic orderLogic) {
        this.orderLogic = orderLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String page;
        Order order;
        List<Order> listOrders;

        try {
            order = setOrderFromRequest(request);
            int idOrder = Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID_ORDER));

            orderLogic.takeOrder(order);
            orderLogic.deleteOrderById(idOrder);
            listOrders = orderLogic.getAllOrders();

            session.setAttribute("orders", listOrders);
            request.setAttribute("messageTakeOrder", "success");
            page = JspPage.LIBRARIAN_ORDERS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.REDIRECT);
        return router;
    }

    private Order setOrderFromRequest(HttpServletRequest request) {
        Order order = new Order();
        User user = new User();
        Book book = new Book();
        user.setId(Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID_READER)));
        book.setId(Integer.parseInt(request.getParameter(RequestParameter.PARAM_ID_BOOK)));
        order.setUser(user);
        order.setBook(book);
        Date dateBorrow = Date.valueOf(request.getParameter(RequestParameter.PARAM_DATE_BORROW));
        Date dateReturn = Date.valueOf(request.getParameter(RequestParameter.PARAM_DATE_RETURN));
        String methodBorrow = request.getParameter(RequestParameter.PARAM_METHOD_BORROW);

        if (InputParamValidator.isValidateOrderData(dateBorrow, dateReturn, methodBorrow)) {
            order.setDateBorrow(dateBorrow);
            order.setDateReturn(dateReturn);
            order.setMethodBorrow(methodBorrow);
        }
        return order;
    }
}
