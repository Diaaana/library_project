package by.radomskaya.project.command;

import by.radomskaya.project.command.admin.*;
import by.radomskaya.project.command.common.ChangeLocaleCommand;
import by.radomskaya.project.command.common.LoginCommand;
import by.radomskaya.project.command.common.LogoutCommond;
import by.radomskaya.project.command.user.*;
import by.radomskaya.project.logic.*;

public enum CommandType {
    LOCALE(new ChangeLocaleCommand()),
    LOGIN(new LoginCommand(new ReaderLogic(), new AdminLogic(), new LibrarianLogic())),
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
    DELETE_LIBRARIAN(new DeleteLibrarianCommand(new LibrarianLogic())),
    ADD_TO_CART(new AddToCartCommand(new OrderLogic())),
    GET_PERSONAL_ORDERS(new GetPersonalOrdersCommand(new OrderLogic()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
