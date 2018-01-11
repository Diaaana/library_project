package by.radomskaya.project.command;

import by.radomskaya.project.command.common.ChangeLocale;
import by.radomskaya.project.command.user.LoginCommand;
import by.radomskaya.project.command.user.LogoutCommond;
import by.radomskaya.project.command.user.RegistrationCommand;
import by.radomskaya.project.logic.UserLogic;

public enum CommandType {
    LOGIN(new LoginCommand(new UserLogic())),
    LOGOUT(new LogoutCommond()),
    REGISTRATION(new RegistrationCommand(new UserLogic())),
    LOCALE(new ChangeLocale());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
