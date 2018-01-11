package by.radomskaya.project.command.common;

import by.radomskaya.project.command.Command;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        return null;
    }
}
