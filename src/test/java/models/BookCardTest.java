package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookCardTest {
    String title;
    String author;
    String bookCode;
    String category;
    String description;
    BookCard INVERTEBRATE_PALEONTOLOGY;
    BookCard instance;
    @BeforeEach
    void init(){
        title = "Invertebrate Paleontology";
        author = "Prof. Rahaman";
        bookCode = "GLY_IP";
        category ="Geology";
        description = "An introduction to micro fauna and flora fossils of the mid pleistocene to paleocene era";

    }

    @Test
    @DisplayName("Test constructor and getters")
    void testConstructorsAndGetters(){
        instance = new BookCard(title, author, bookCode,  category, description);
        assertAll(
                ()->assertEquals(title, instance.getTitle()),
                ()->assertEquals(author, instance.getAuthor()),
                ()->assertEquals(bookCode, instance.getBookCode()),
                ()->assertEquals(description, instance.getDescription()),
                ()-> assertEquals(category, instance.getCategory()),
                ()-> assertEquals(0, instance.getID_OF_LAST_BOOK_ADDED()),
                ()-> assertEquals(0, instance.getTotalQuantity()),
                ()-> assertEquals(0, instance.getAvailableQuantityInShelf())
        );
    }

    @Test
    @DisplayName("Test setters method")
    void testSetters(){
        instance = new BookCard();
        instance.setTitle(title);
        instance.setAuthor(author);
        instance.setTotalQuantity(3);
        instance.setBookCode(bookCode);
        instance.setCategory(category);
        instance.setDescription(description);
        instance.setID_OF_LAST_BOOK_ADDED(0);
        instance.setAvailableQuantityInShelf(2);

        assertAll(
                ()->assertEquals(title, instance.getTitle()),
                ()->assertEquals(author, instance.getAuthor()),
                ()->assertEquals(bookCode, instance.getBookCode()),
                ()->assertEquals(description, instance.getDescription()),
                ()-> assertEquals(category, instance.getCategory()),
                ()-> assertEquals(0, instance.getID_OF_LAST_BOOK_ADDED()),
                ()-> assertEquals(3, instance.getTotalQuantity()),
                ()-> assertEquals(2, instance.getAvailableQuantityInShelf())
        );
    }



}