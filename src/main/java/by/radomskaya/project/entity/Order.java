package by.radomskaya.project.entity;

public class Order {
    private int id;
    private int numberTicket;
    private Book book;
    private Author author;
    private String dateBorrow;
    private String dateReturn;
    private String methodBorrow;

    public Order() {}

    public Order(int id, int numberTicket, Book book, Author author, String dateBorrow, String dateReturn, String methodBorrow) {
        this.id = id;
        this.numberTicket = numberTicket;
        this.book = book;
        this.author = author;
        this.dateBorrow = dateBorrow;
        this.dateReturn = dateReturn;
        this.methodBorrow = methodBorrow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberTicket() {
        return numberTicket;
    }

    public void setNumberTicket(int numberTicket) {
        this.numberTicket = numberTicket;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getDateBorrow() {
        return dateBorrow;
    }

    public void setDateBorrow(String dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public String getMethodBorrow() {
        return methodBorrow;
    }

    public void setMethodBorrow(String methodBorrow) {
        this.methodBorrow = methodBorrow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (numberTicket != order.numberTicket) return false;
        if (book != null ? !book.equals(order.book) : order.book != null) return false;
        if (author != null ? !author.equals(order.author) : order.author != null) return false;
        if (dateBorrow != null ? !dateBorrow.equals(order.dateBorrow) : order.dateBorrow != null) return false;
        if (dateReturn != null ? !dateReturn.equals(order.dateReturn) : order.dateReturn != null) return false;
        return methodBorrow != null ? methodBorrow.equals(order.methodBorrow) : order.methodBorrow == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + numberTicket;
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (dateBorrow != null ? dateBorrow.hashCode() : 0);
        result = 31 * result + (dateReturn != null ? dateReturn.hashCode() : 0);
        result = 31 * result + (methodBorrow != null ? methodBorrow.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", numberTicket=" + numberTicket +
                ", book=" + book +
                ", author=" + author +
                ", dateBorrow='" + dateBorrow + '\'' +
                ", dateReturn='" + dateReturn + '\'' +
                ", methodBorrow='" + methodBorrow + '\'' +
                '}';
    }
}
