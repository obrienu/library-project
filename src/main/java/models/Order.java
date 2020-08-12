package models;

public class Order {
    private User user;
    private String bookCode;

    public Order() {
    }

    public Order(User user, String bookCode) {
        this.user = user;
        this.bookCode = bookCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    @Override
    public String toString() {
        return user.toString() +
                ", bookId='" + bookCode + '\'';
    }


}
