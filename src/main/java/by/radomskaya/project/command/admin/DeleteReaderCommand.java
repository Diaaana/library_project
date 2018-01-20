package by.radomskaya.project.command.admin;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Reader;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.ReaderLogic;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.ADMIN_READERS_PAGE;

public class DeleteReaderCommand implements Command {
    private final String PARAM_ID_READER = "id";
    private ReaderLogic readerLogic;

    public DeleteReaderCommand(ReaderLogic readerLogic) {
        this.readerLogic = readerLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        List<Reader> listReaders;
        int numberTicket = Integer.parseInt(request.getParameter(PARAM_ID_READER));

        try {
            if (readerLogic.deleteReader(numberTicket)) {
                listReaders = readerLogic.getReaders();
                request.setAttribute("readers", listReaders);
                page = ADMIN_READERS_PAGE;
            }
        } catch (DAOException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
