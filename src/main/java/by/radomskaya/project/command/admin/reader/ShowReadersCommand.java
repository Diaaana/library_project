package by.radomskaya.project.command.admin.reader;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.LogicException;
import by.radomskaya.project.logic.ReaderLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowReadersCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(ShowReadersCommand.class);
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

            request.setAttribute(ParameterConstants.PARAM_READERS, listUser);
            page = JspPageConstants.ADMIN_READERS_PAGE;
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
