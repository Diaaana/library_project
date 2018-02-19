package by.radomskaya.project.entity;

public class Order {
    private int id;
    private int numberTicket;
    private String dateBorrow;
    private String dateReturn;
    private String book;
    private String author;
    private String methodBorrow;

    public Order() {}

    public Order(int id, int numberTicket, String dateBorrow, String dateReturn, String book, String author, String methodBorrow) {
        this.id = id;
        this.numberTicket = numberTicket;
        this.dateBorrow = dateBorrow;
        this.dateReturn = dateReturn;
        this.book = book;
        this.author = author;
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

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
        if (!dateBorrow.equals(order.dateBorrow)) return false;
        if (!dateReturn.equals(order.dateReturn)) return false;
        if (!book.equals(order.book)) return false;
        if (!author.equals(order.author)) return false;
        return methodBorrow.equals(order.methodBorrow);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + numberTicket;
        result = 31 * result + dateBorrow.hashCode();
        result = 31 * result + dateReturn.hashCode();
        result = 31 * result + book.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + methodBorrow.hashCode();
        return result;
    }
}
