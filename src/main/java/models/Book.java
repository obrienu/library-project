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

    /**
     * gets book title
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets book title
     * @return
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets book author
     * @return
     */
    public String getAuthor() {
        return author;
    }


    /**
     * sets book author
     * @return
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * gets book code
     * @return
     */
    public String getBookCode() {
        return bookCode;
    }

    /**
     * gets book code
     * @return
     */
    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }


    /**
     * gets book Id
     * @return
     */
    public long getBookId() {
        return bookId;
    }

    /**
     * sets book Id
     * @return
     */
    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    /**
     * gets book books availability in shelf
     * @return
     */
    public boolean isInShelf() {
        return inShelf;
    }

    /**
     * sets book books availability
     * @return
     */
    public void setInShelf(boolean inShelf) {
        this.inShelf = inShelf;
    }

    /**
     * gets book books entryDate
     * @return
     */
    public String getEntryDate() {
        return String.valueOf(entryDate);
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

