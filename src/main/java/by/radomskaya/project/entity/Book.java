package by.radomskaya.project.entity;

public class Book extends Entity {
    private String isbn;
    private String tittle;
    private String genre;
    private String dateEdition;
    private String placeEdition;
    private String publisher;
    private int numberCopies;

    public Book() {}

    public Book(String isbn, String tittle, String genre, String dateEdition, String placeEdition, String publisher, int numberCopies) {
        this.isbn = isbn;
        this.tittle = tittle;
        this.genre = genre;
        this.dateEdition = dateEdition;
        this.placeEdition = placeEdition;
        this.publisher = publisher;
        this.numberCopies = numberCopies;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDateEdition() {
        return dateEdition;
    }

    public void setDateEdition(String dateEdition) {
        this.dateEdition = dateEdition;
    }

    public String getPlaceEdition() {
        return placeEdition;
    }

    public void setPlaceEdition(String placeEdition) {
        this.placeEdition = placeEdition;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberCopies() {
        return numberCopies;
    }

    public void setNumberCopies(int numberCopies) {
        this.numberCopies = numberCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (numberCopies != book.numberCopies) return false;
        if (!isbn.equals(book.isbn)) return false;
        if (!tittle.equals(book.tittle)) return false;
        if (!genre.equals(book.genre)) return false;
        if (!dateEdition.equals(book.dateEdition)) return false;
        if (!placeEdition.equals(book.placeEdition)) return false;
        return publisher.equals(book.publisher);
    }

    @Override
    public int hashCode() {
        int result = isbn.hashCode();
        result = 31 * result + tittle.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + dateEdition.hashCode();
        result = 31 * result + placeEdition.hashCode();
        result = 31 * result + publisher.hashCode();
        result = 31 * result + numberCopies;
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", tittle='" + tittle + '\'' +
                ", genre='" + genre + '\'' +
                ", dateEdition='" + dateEdition + '\'' +
                ", placeEdition='" + placeEdition + '\'' +
                ", publisher='" + publisher + '\'' +
                ", numberCopies=" + numberCopies +
                '}';
    }
}
