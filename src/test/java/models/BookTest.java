package models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    Book INVERTEBRATE_PALEONTOLOGY;
    String title = "Invertebrate Paleontology";
    String author = "Prof. Rahaman";
    String bookCode = "GLY_IP";
    String edition = "1st Edition";
    String date = LocalDate.now().toString();

    @Test
    void testConstructor(){
        INVERTEBRATE_PALEONTOLOGY = new Book(title, author, bookCode, 1, edition);
        assertAll(
                ()->assertEquals(title, INVERTEBRATE_PALEONTOLOGY.getTitle()),
                ()->assertEquals(author, INVERTEBRATE_PALEONTOLOGY.getAuthor()),
                ()-> assertEquals(bookCode, INVERTEBRATE_PALEONTOLOGY.getBookCode()),
                ()-> assertEquals(1, INVERTEBRATE_PALEONTOLOGY.getBookId()),
                ()-> assertEquals(date, INVERTEBRATE_PALEONTOLOGY.getEntryDate()),
                ()-> assertTrue(INVERTEBRATE_PALEONTOLOGY.isInShelf())
        );

    }

    @Test
    void testGettersAndSetters(){
        INVERTEBRATE_PALEONTOLOGY = new Book();
        INVERTEBRATE_PALEONTOLOGY.setAuthor(author);
        INVERTEBRATE_PALEONTOLOGY.setInShelf(true);
        INVERTEBRATE_PALEONTOLOGY.setBookCode(bookCode);
        INVERTEBRATE_PALEONTOLOGY.setTitle(title);
        INVERTEBRATE_PALEONTOLOGY.setBookId(1);

        assertAll(
                ()->assertEquals(title, INVERTEBRATE_PALEONTOLOGY.getTitle()),
                ()->assertEquals(author, INVERTEBRATE_PALEONTOLOGY.getAuthor()),
                ()-> assertEquals(bookCode, INVERTEBRATE_PALEONTOLOGY.getBookCode()),
                ()-> assertEquals(1, INVERTEBRATE_PALEONTOLOGY.getBookId()),
                ()-> assertTrue(INVERTEBRATE_PALEONTOLOGY.isInShelf())
        );
    }

}