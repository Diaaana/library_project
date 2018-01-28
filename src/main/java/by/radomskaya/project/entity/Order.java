package by.radomskaya.project.entity;


import java.sql.Date;

public class Order {
    private int id;
    private int numberTicket;
    private Book book;
    private Author author;
    private Date dateBorrow;
    private Date dateReturn;
    private String methodBorrow;

    public Order() {}

    public Order(int id, int numberTicket, Book book, Author author, Date dateBorrow, Date dateReturn, String methodBorrow) {
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

    public Date getDateBorrow() {
        return dateBorrow;
    }

    public void setDateBorrow(Date dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
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
        if (!book.equals(order.book)) return false;
        if (!author.equals(order.author)) return false;
        if (!dateBorrow.equals(order.dateBorrow)) return false;
        if (!dateReturn.equals(order.dateReturn)) return false;
        return methodBorrow.equals(order.methodBorrow);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + numberTicket;
        result = 31 * result + book.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + dateBorrow.hashCode();
        result = 31 * result + dateReturn.hashCode();
        result = 31 * result + methodBorrow.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", numberTicket=" + numberTicket +
                ", book=" + book +
                ", author=" + author +
                ", dateBorrow=" + dateBorrow +
                ", dateReturn=" + dateReturn +
                ", methodBorrow='" + methodBorrow + '\'' +
                '}';
    }
}
