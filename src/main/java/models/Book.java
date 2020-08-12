package models;

import java.time.LocalDate;

public class Book  {
    private String title;
    private String author;
    private String bookCode;
    private long bookId;
    private String edition;
    private boolean inShelf;
    private LocalDate entryDate;
    private LocalDate lastLoanedOut;

    public Book() {
    }

    public Book(String title, String author, String bookCode, long bookId, String edition) {
        this.title = title;
        this.author = author;
        this.bookCode = bookCode;
        this.bookId = bookId;
        this.inShelf = true;
        this.entryDate = LocalDate.now();
        this.edition = edition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public boolean isInShelf() {
        return inShelf;
    }

    public void setInShelf(boolean inShelf) {
        this.inShelf = inShelf;
    }

    public String getEntryDate() {
        return String.valueOf(entryDate);
    }

    public String getLastLoanedOut() {
        return String.valueOf(lastLoanedOut);
    }

    public void setLastLoanedOut() {
        this.lastLoanedOut = LocalDate.now();
    }



    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", bookCode='" + bookCode + '\'' +
                ", bookId='" + bookId + '\'' +
                ", edition='" + edition + '\'' +
                ", inShelf=" + inShelf +
                ", entryDate=" + entryDate +
                ", lastLoanedOut=" + lastLoanedOut +
                '}';
    }
}

