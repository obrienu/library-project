package models;


import java.util.Collection;
import java.util.TreeSet;


/*
* The BookCard class creates books based of its model and keeps tab of total quantity of copies
* added and quantities of books available on the shelf.
*/

public class BookCard {
    private String title;
    private String author;
    private String bookCode;
    private String category;
    private String description;
    private int totalQuantity;
    private int availableQuantityInShelf;
    private long ID_OF_LAST_BOOK_ADDED;

    public BookCard() {

    }

    /**
     *
     * @param title
     * @param author
     * @param bookCode
     * @param category
     * @param description
     */
    public BookCard(String title, String author, String bookCode, String category, String description) {
        this.title = title;
        this.author = author;
        this.bookCode = bookCode;
        this.category = category;
        this.description = description;
        this.totalQuantity = 0;
        this.availableQuantityInShelf = 0;
        this.ID_OF_LAST_BOOK_ADDED = 0;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int quantity) {
        this.totalQuantity = quantity;
    }

    public int getAvailableQuantityInShelf() {
        return availableQuantityInShelf;
    }

    public void setAvailableQuantityInShelf(int quantity) {
         this.availableQuantityInShelf = quantity;
    }

    public long getID_OF_LAST_BOOK_ADDED() {
        return ID_OF_LAST_BOOK_ADDED;
    }

    public void setID_OF_LAST_BOOK_ADDED(long id) {
        this.ID_OF_LAST_BOOK_ADDED = id;
    }





}
