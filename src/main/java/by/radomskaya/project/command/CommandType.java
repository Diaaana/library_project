package by.radomskaya.project.command;

import by.radomskaya.project.command.admin.AddBookCommand;
import by.radomskaya.project.command.admin.AddLibrarianCommand;
import by.radomskaya.project.command.admin.ShowBooksCommand;
import by.radomskaya.project.command.admin.ShowLibrariansCommand;
import by.radomskaya.project.command.common.ChangeLocaleCommand;
import by.radomskaya.project.command.common.LoginCommand;
import by.radomskaya.project.command.common.LogoutCommond;
import by.radomskaya.project.command.user.FindBookCommand;
import by.radomskaya.project.command.user.RegistrationCommand;
import by.radomskaya.project.logic.AdminLogic;
import by.radomskaya.project.logic.LibrarianLogic;
import by.radomskaya.project.logic.UserLogic;

public enum CommandType {
    LOCALE(new ChangeLocaleCommand()),
    LOGIN(new LoginCommand(new UserLogic(), new AdminLogic(), new LibrarianLogic())),
    LOGOUT(new LogoutCommond()),
    REGISTRATION(new RegistrationCommand(new UserLogic())),
    ADD_BOOK(new AddBookCommand(new AdminLogic())),
    ADD_LIBRARIAN(new AddLibrarianCommand(new AdminLogic())),
    SHOW_BOOKS(new ShowBooksCommand(new AdminLogic())),
    SHOW_LIBRARIANS(new ShowLibrariansCommand(new AdminLogic())),
    FIND_BOOK(new FindBookCommand(new UserLogic()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
