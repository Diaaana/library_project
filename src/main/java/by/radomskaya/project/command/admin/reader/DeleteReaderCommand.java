package by.radomskaya.project.command.admin.reader;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.constant.JspPageConstants;
import by.radomskaya.project.constant.MessageConstants;
import by.radomskaya.project.constant.ParameterConstants;
import by.radomskaya.project.constant.PropertyKeys;
import by.radomskaya.project.controller.Router;
import by.radomskaya.project.entity.User;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.LogicException;
import by.radomskaya.project.logic.ReaderLogic;
import by.radomskaya.project.manager.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DeleteReaderCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(DeleteReaderCommand.class);
    private ReaderLogic readerLogic;

    public DeleteReaderCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        String locale = request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE) == null ? ParameterConstants.DEFAULT_LOCALE : request.getSession().getAttribute(ParameterConstants.PARAM_LOCALE).toString();
        String page = null;
        List<User> listUsers;

        try {
            int idUser = Integer.parseInt(request.getParameter(ParameterConstants.PARAM_ID_READER));

            if (readerLogic.deleteReader(idUser)) {
                listUsers = readerLogic.getReaders();
                request.setAttribute(ParameterConstants.PARAM_READERS, listUsers);
                request.setAttribute(MessageConstants.MESSAGE_DELETE_READER, MessageManager.getLocale(locale).getMessage(PropertyKeys.DELETE_READER_MESSAGE));
                page = JspPageConstants.ADMIN_READERS_PAGE;
            }
        } catch (LogicException e) {
            LOGGER.error(e);
        }

        router.setPagePath(page);
        router.setRoute(Router.RouteType.FORWARD);
        return router;
    }
}
