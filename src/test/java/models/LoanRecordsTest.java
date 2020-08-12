package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanRecordsTest {
    LoanRecords record;

    @BeforeEach
    void init(){
        record = new LoanRecords();

    }

    @Test
    void testConstructor(){
        record = new LoanRecords(12345, "PHY", 123);

        String today = LocalDate.now().toString();
        String twoWeeksTime = LocalDate.now().plusWeeks(2).toString();

        assertAll(
                ()-> assertEquals(12345, record.getLibraryId(), "Should return correct library id"),
                ()-> assertEquals("PHY", record.getBookCode(), "Method should return correct, book code"),
                ()->assertEquals(123, record.getBookId(), "Method should return correct book id"),
                ()->assertEquals(today, record.getDateBorrowed(), "Method should return correct date book was borrowed"),
                ()->assertEquals(twoWeeksTime, record.getDateDueForReturn(), "Method should return correct due date "),
                ()-> assertFalse(record.isReturned(), "Method to return false")
        );
    }



    @Test
    void testSetters() {
        record.setBookCode("PHY");
        record.setBookId(123);
        record.setLibraryId(12345);

        assertAll(
                ()-> assertEquals(12345, record.getLibraryId(), "Should return correct library id"),
                ()-> assertEquals("PHY", record.getBookCode(), "Method should return correct, book code"),
                ()->assertEquals(123, record.getBookId(), "Method should return correct book id")
        );
    }




}