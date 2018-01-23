package by.radomskaya.project.command;

import by.radomskaya.project.command.admin.author.DeleteAuthorCommand;
import by.radomskaya.project.command.admin.author.EditAuthorCommand;
import by.radomskaya.project.command.admin.author.ShowAuthorsCommand;
import by.radomskaya.project.command.admin.author.UpdateAuthorCommand;
import by.radomskaya.project.command.admin.book.*;
import by.radomskaya.project.command.admin.librarian.*;
import by.radomskaya.project.command.admin.reader.DeleteReaderCommand;
import by.radomskaya.project.command.admin.reader.ShowReadersCommand;
import by.radomskaya.project.command.common.ChangeLocaleCommand;
import by.radomskaya.project.command.common.LoginCommand;
import by.radomskaya.project.command.common.LogoutCommond;
import by.radomskaya.project.command.librarian.CheckOrderCommand;
import by.radomskaya.project.command.librarian.ShowOrdersCommand;
import by.radomskaya.project.command.librarian.TakeOrderCommand;
import by.radomskaya.project.command.user.*;
import by.radomskaya.project.command.user.book.FindBookByAuthorCommand;
import by.radomskaya.project.command.user.book.FindBookByTittleCommand;
import by.radomskaya.project.command.user.book.GetBooksCommand;
import by.radomskaya.project.command.user.order.AddToCartCommand;
import by.radomskaya.project.command.user.order.DeleteOrderCommand;
import by.radomskaya.project.command.user.order.GetPersonalOrdersCommand;
import by.radomskaya.project.logic.*;

public enum CommandType {
    LOCALE(new ChangeLocaleCommand()),
    LOGIN(new LoginCommand(new ReaderLogic(), new AdminLogic(), new LibrarianLogic())),
    LOGOUT(new LogoutCommond()),
    REGISTRATION(new RegistrationCommand(new AdminLogic(), new ReaderLogic(), new LibrarianLogic())),
    ADD_BOOK(new AddBookCommand(new BookLogic(), new AuthorLogic())),
    EDIT_BOOK(new EditBookCommand(new BookLogic())),
    UPDATE_BOOK(new UpdateBookCommand(new BookLogic())),
    EDIT_AUTHOR(new EditAuthorCommand(new AuthorLogic())),
    UPDATE_AUTHOR(new UpdateAuthorCommand(new AuthorLogic())),
    ADD_LIBRARIAN(new AddLibrarianCommand(new LibrarianLogic())),
    EDIT_LIBRARIAN(new EditLibrarianCommand(new LibrarianLogic())),
    UPDATE_LIBRARIAN(new UpdateLibrarianCommand(new LibrarianLogic())),
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
    GET_PERSONAL_ORDERS(new GetPersonalOrdersCommand(new OrderLogic())),
    SHOW_ORDERS(new ShowOrdersCommand(new OrderLogic())),
    CHECK_ORDER(new CheckOrderCommand()),
    TAKE_ORDER(new TakeOrderCommand(new OrderLogic())),
    DELETE_ORDER(new DeleteOrderCommand(new OrderLogic()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
