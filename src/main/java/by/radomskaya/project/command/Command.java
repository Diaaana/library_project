package by.radomskaya.project.command;

import by.radomskaya.project.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request) throws CommandException;
}
