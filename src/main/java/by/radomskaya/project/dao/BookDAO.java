package by.radomskaya.project.dao;

import by.radomskaya.project.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks();
    void addBook(Book book);
}
