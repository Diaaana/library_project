package by.radomskaya.project.command.admin.reader;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPage;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.ReaderLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowReadersCommand implements Command {
    private ReaderLogic readerLogic;

    public ShowReadersCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String page = null;
        List<User> listUser;

        try {
            listUser = readerLogic.getReaders();

            request.setAttribute("readers", listUser);
            page = JspPage.ADMIN_READERS_PAGE;
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
