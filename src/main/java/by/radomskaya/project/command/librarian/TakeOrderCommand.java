package by.radomskaya.project.command.librarian;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class TakeOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
