package by.radomskaya.project.command;

import by.radomskaya.project.command.admin.*;
import by.radomskaya.project.command.common.ChangeLocaleCommand;
import by.radomskaya.project.command.common.LoginCommand;
import by.radomskaya.project.command.common.LogoutCommond;
import by.radomskaya.project.command.user.FindBookByAuthorCommand;
import by.radomskaya.project.command.user.FindBookByTittleCommand;
import by.radomskaya.project.command.user.GetBooksCommand;
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
    SHOW_AUTHORS(new ShowAuthorsCommand(new AdminLogic())),
    SHOW_READERS(new ShowReadersCommand(new AdminLogic())),
    SHOW_LIBRARIANS(new ShowLibrariansCommand(new AdminLogic())),
    GET_BOOKS(new GetBooksCommand(new UserLogic())),
    FIND_BOOK_BY_TITTLE(new FindBookByTittleCommand(new UserLogic())),
    FIND_BOOK_BY_AUTHOR(new FindBookByAuthorCommand(new UserLogic())),
    DELETE_BOOK(new DeleteBookCommand(new AdminLogic())),
    DELETE_AUTHOR(new DeleteAuthorCommand(new AdminLogic())),
    DELETE_READER(new DeleteReaderCommand(new AdminLogic())),
    DELETE_LIBRARIAN(new DeleteLibrarianCommand(new AdminLogic()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
