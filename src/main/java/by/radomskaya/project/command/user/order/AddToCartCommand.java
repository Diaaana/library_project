package by.radomskaya.project.command.user.order;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.LogicException;
import by.radomskaya.project.logic.BookLogic;
import by.radomskaya.project.logic.OrderLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddToCartCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(AddToCartCommand.class);
    private OrderLogic orderLogic;
    private BookLogic bookLogic;

    public AddToCartCommand(OrderLogic orderLogic, BookLogic bookLogic) {
        this.orderLogic = orderLogic;
        this.bookLogic = bookLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router = new Router();
        String page = null;
        List<Book> listBooks;

        try {
            int idUser = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_READER));
            int idBook = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_BOOK));
            int idAuthor = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_AUTHOR));

            orderLogic.addToCart(idUser, idBook, idAuthor);
            listBooks = bookLogic.getBooks();
            session.setAttribute(ParameterConstants.PARAM_BOOKS, listBooks);
            page = JspPageConstants.USER_BOOKS_PAGE;

        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.REDIRECT);
        return router;
    }
}
