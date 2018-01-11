package by.radomskaya.project.command.user;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class MakeOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
