package by.radomskaya.project.command;

import by.radomskaya.project.command.user.LoginCommand;
import by.radomskaya.project.command.user.RegistrationCommand;
import by.radomskaya.project.logic.UserLogic;

public enum CommandType {
    LOGIN(new LoginCommand(new UserLogic())),
    REGISTRATION(new RegistrationCommand(new UserLogic()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
