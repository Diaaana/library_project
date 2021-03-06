package by.radomskaya.project.command.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.entity.Order;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class CheckOrderCommand implements Command {
    public CheckOrderCommand() { }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page;
        Order order = new Order();
        Book book = new Book();
        User user = new User();

        String tittle = request.getParameter(ParameterConstants.PARAM_TITTLE);
        String mail = request.getParameter(ParameterConstants.PARAM_MAIL);
        int idOrder = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_ORDER));
        int idUser = Integer.parseInt(request.getParameter((ParameterConstants.PARAM_ID_READER)));
        int idBook = Integer.parseInt(request.getParameter((ParameterConstants.PARAM_ID_BOOK)));

        book.setId(idBook);
        book.setTittle(tittle);
        user.setId(idUser);
        user.setMail(mail);
        order.setId(idOrder);
        order.setUser(user);
        order.setBook(book);

        request.setAttribute(ParameterConstants.PARAM_ORDER, order);
        page = JspPageConstants.LIBRARIAN_TAKE_ORDER_PAGE;

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
