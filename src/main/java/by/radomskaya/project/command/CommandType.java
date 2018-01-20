package by.radomskaya.project.command;

import by.radomskaya.project.command.admin.*;
import by.radomskaya.project.command.common.ChangeLocaleCommand;
import by.radomskaya.project.command.common.LoginCommand;
import by.radomskaya.project.command.common.LogoutCommond;
import by.radomskaya.project.command.user.FindBookByAuthorCommand;
import by.radomskaya.project.command.user.FindBookByTittleCommand;
import by.radomskaya.project.command.user.GetBooksCommand;
import by.radomskaya.project.command.user.RegistrationCommand;
import by.radomskaya.project.logic.*;

public enum CommandType {
    LOCALE(new ChangeLocaleCommand()),
    LOGIN(new LoginCommand(new UserLogic(), new AdminLogic(), new LibrarianLogic())),
    LOGOUT(new LogoutCommond()),
    REGISTRATION(new RegistrationCommand(new UserLogic())),
    ADD_BOOK(new AddBookCommand(new BookLogic(), new AuthorLogic())),
    ADD_LIBRARIAN(new AddLibrarianCommand(new LibrarianLogic())),
    SHOW_BOOKS(new ShowBooksCommand(new BookLogic())),
    SHOW_AUTHORS(new ShowAuthorsCommand(new AuthorLogic())),
    SHOW_READERS(new ShowReadersCommand(new ReaderLogic())),
    SHOW_LIBRARIANS(new ShowLibrariansCommand(new LibrarianLogic())),
    GET_BOOKS(new GetBooksCommand(new BookLogic())),
    FIND_BOOK_BY_TITTLE(new FindBookByTittleCommand(new BookLogic())),
    FIND_BOOK_BY_AUTHOR(new FindBookByAuthorCommand(new BookLogic())),
    DELETE_BOOK(new DeleteBookCommand(new BookLogic())),
    DELETE_AUTHOR(new DeleteAuthorCommand(new AuthorLogic())),
    DELETE_READER(new DeleteReaderCommand(new ReaderLogic())),
    DELETE_LIBRARIAN(new DeleteLibrarianCommand(new LibrarianLogic()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
