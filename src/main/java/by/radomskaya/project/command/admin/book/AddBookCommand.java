package by.radomskaya.project.command.admin.book;

import by.radomskaya.project.command.Command;
import by.radomskaya.project.entity.Author;
import by.radomskaya.project.entity.Book;
import by.radomskaya.project.exception.CommandException;
import by.radomskaya.project.exception.DAOException;
import by.radomskaya.project.logic.AuthorLogic;
import by.radomskaya.project.logic.BookLogic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

import static by.radomskaya.project.constant.PageConstant.ADMIN_ADD_BOOKS_PAGE;
import static by.radomskaya.project.constant.PageConstant.ADMIN_BOOKS_PAGE;


public class AddBookCommand implements Command {
    private final static String PARAM_ISBN = "isbn";
    private final static String PARAM_TITTLE = "tittle";
    private final static String PARAM_AUTHOR_SURNAME = "surname";
    private final static String PARAM_AUTHOR_NAME = "name";
    private final static String PARAM_AUTHOR_MIDDLE_NAME = "middle_name";
    private final static String PARAM_AUTHOR_COUNTRY = "country";
    private final static String PARAM_GENRE = "genre";
    private final static String PARAM_DATA_EDITION = "date_edition";
    private final static String PARAM_PLACE_EDITION = "place_edition";
    private final static String PARAM_PUBLISHER = "publisher";
    private final static String PARAM_NUMBER_COPIES = "number_copies";
    private final static String PARAM_IMAGE = "image";
    private BookLogic bookLogic;
    private AuthorLogic authorLogic;

    public AddBookCommand(BookLogic bookLogic, AuthorLogic authorLogic) {
        this.bookLogic = bookLogic;
        this.authorLogic = authorLogic;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        Book book = new Book();
        Author author = new Author();
        List<Book> listBooks;
        String[] genres = request.getParameterValues(PARAM_GENRE);
        for (int i = 0; i < genres.length; i++) {
            System.out.println(genres[i]);
        }
        try {
            book.setIsbn(request.getParameter(PARAM_ISBN));
            book.setTittle(request.getParameter(PARAM_TITTLE));
            author.setSurname(request.getParameter(PARAM_AUTHOR_SURNAME));
            author.setName(request.getParameter(PARAM_AUTHOR_NAME));
            author.setMiddleName(request.getParameter(PARAM_AUTHOR_MIDDLE_NAME));
            author.setCountryBirth(request.getParameter(PARAM_AUTHOR_COUNTRY));
            book.setAuthor(author);
            book.setGenres(genres);
            book.setDateEdition(Date.valueOf(request.getParameter(PARAM_DATA_EDITION)));
            book.setPlaceEdition(request.getParameter(PARAM_PLACE_EDITION));
            book.setPublisher(request.getParameter(PARAM_PUBLISHER));
            book.setNumberCopies(Integer.parseInt(request.getParameter(PARAM_NUMBER_COPIES)));
            Part filePart = request.getPart(PARAM_IMAGE);
            String imageName = getImageName(filePart);
            book.setImage(imageName);

            if (authorLogic.addAuthor(author) && bookLogic.addBook(book)) {
                bookLogic.addBookAndGenre(genres);
                //request.setAttribute("success", "Все хорошо");
                listBooks = bookLogic.getBooks();
                request.setAttribute("books", listBooks);
                page = ADMIN_BOOKS_PAGE;
            } else {
                page = ADMIN_ADD_BOOKS_PAGE;
            }
        } catch (ServletException | DAOException | IOException e) {
            throw new CommandException(e);
        }

        return page;
    }

    private String getImageName(Part filePart) {
        String name = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        return name;
    }
}
