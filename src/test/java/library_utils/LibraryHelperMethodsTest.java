package library_utils;

import models.Book;
import models.BookCard;
import models.LoanRecords;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class LibraryHelperMethodsTest {

    User headTeacher, juniorTeacher, seniorStudent1, seniorStudent2, juniorStudent, juniorStudent2;
    String title;
    String author;
    String bookCode;
    String category;
    String description;
    BookCard INVERTEBRATE_PALEONTOLOGY;

    @BeforeEach
    void setUp() {

            title = "Invertebrate Paleontology";
            author = "Prof. Rahaman";
            bookCode = "GLY_IP";
            category ="Geology";
            description = "An introduction to micro fauna and flora fossils of the mid pleistocene to paleocene era";

            INVERTEBRATE_PALEONTOLOGY = new BookCard(title, author, bookCode,  category, description);
            //creates five cooks
            LibraryHelperMethods.addBook(INVERTEBRATE_PALEONTOLOGY,5, "1st Edition");


        try{
            headTeacher = new User("O'Brien", "Longe", "6 Asanjo", "2011-02-28",
                    "Male", 1234L, "teacher");
            juniorTeacher = new User("John", "Kimber", "6 Asanjo", "2011-02-28",
                    "Male", 1434L, "teacher");
            seniorStudent1 = new User("Kelvin", "Kim", "6 Asanjo", "2011-02-23",
                    "F", 134L, "senior_student");
            seniorStudent2 = new User("Harry", "Kim", "6 Asanjo", "2001-02-23",
                    "F", 137784L, "senior_student");
            juniorStudent = new User("Harry", "Kim", "6 Asanjo", "2001-02-23",
                    "F", 132784L, "junior_student");
            juniorStudent2 = new User("James", "Okon", "6 Asanjo", "2001-02-23",
                    "F", 184L, "junior_student");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown(){
        MockDataBase.listOfBooks.clear();
        MockDataBase.listOfBookCard.clear();
        MockDataBase.PRIORITY_ORDERS.clear();
        MockDataBase.FIFO_ORDERS.clear();
        MockDataBase.records.clear();
    }



    @Test
    void takeOrder() {
        LibraryHelperMethods.takeOrder(seniorStudent2, "PHY110");
        LibraryHelperMethods.takeOrder(seniorStudent1, "PHY110");
        LibraryHelperMethods.takeOrder(juniorStudent2, "PHY114");
        LibraryHelperMethods.takeOrder(juniorStudent, "PHY110");
        LibraryHelperMethods.takeOrder(juniorTeacher, "PHY114");
        LibraryHelperMethods.takeOrder(headTeacher, "PHY114");
        assertEquals(6, MockDataBase.PRIORITY_ORDERS.size(), "Method should add 6 book orders to the PriorityOrderList");
        assertEquals(6, MockDataBase.FIFO_ORDERS.size(), "Method should add 6 book orders to the PriorityOrderList");
    }


    @Test
    @DisplayName("Test bookReturned Method")
    void bookReturned() {
        //Create book cards with all book information and record
        BookCard PHYSICS = LibraryHelperMethods.createBookCard("Physics", "Steve", "PHY",  "Science", "description");
        BookCard CHEMISTRY = LibraryHelperMethods.createBookCard("Chemistry", "John", "CHM",  "Science", "description");

        //Add 1 of each book to the library shelf
        Book copyOfPhysics = LibraryHelperMethods.addBook(PHYSICS, 1, "1st Edition").get(0);
        Book copyOfChemistry =  LibraryHelperMethods.addBook(CHEMISTRY, 1, "1st Edition").get(0);

        //loan out the books;
        LibraryHelperMethods.loanOutBook(PHYSICS, 1234);
        LibraryHelperMethods.loanOutBook(CHEMISTRY, 1234);

        // gets the record form the records database
        LoanRecords record = LibraryHelperMethods.getRecord(1234, copyOfPhysics.getBookCode(), copyOfPhysics.getBookId());

        //Returns the books
        try{
            LibraryHelperMethods.returnBook(PHYSICS.getBookCode(), copyOfPhysics.getBookId(), 1234);
        }catch (Exception e){
            e.printStackTrace();
        }

        assertAll(
                ()-> assertEquals(1, PHYSICS.getAvailableQuantityInShelf(), "Method should increase the" +
                        "available quantity in shelf by 1"),
                ()->assertTrue(copyOfPhysics.isInShelf(), "Method should set the status of the copy of the book " +
                        "returned back to true"),
                ()-> assertTrue(record.isReturned(), "Method should set the is returned field of record to true")
        );

    }




    @Test
    @DisplayName("Test loanOutBook Method")
    void loanOutBook() {
        //Create book cards with all book information and record
        BookCard PHYSICS = new BookCard("Physics", "Steve", "PHY",  "Science", "description");
        BookCard CHEMISTRY = new BookCard("Chemistry", "John", "CHM",  "Science", "description");

            //Add 1 of each book to the library shelf
        Book copyOfPhysics = LibraryHelperMethods.addBook(PHYSICS, 1, "1st Edition").get(0);
        Book copyOfChemistry =  LibraryHelperMethods.addBook(CHEMISTRY, 1, "1st Edition").get(0);

        LibraryHelperMethods.loanOutBook(PHYSICS, 1234);
        LibraryHelperMethods.loanOutBook(CHEMISTRY, 1234);

        assertAll(
                ()->assertEquals(0, CHEMISTRY.getAvailableQuantityInShelf(), "Method should reduce quantity of book in shelf by 1"),
                ()->assertFalse(copyOfChemistry.isInShelf(), "Method should set the status of the book give out to unavailable"),
                //trying to loan out another copy of chemistry shouldn't be possible when the book
                // is no longer available on shelf
                ()->assertEquals("Book taken", LibraryHelperMethods.loanOutBook(CHEMISTRY, 1234)
                        , "Method should return a message when the book is no longer available"),
                ()->assertEquals(2, MockDataBase.records.size(), "Method should create a record and add " +
                "records created to the records database")
        );



    }

    @Test
    @DisplayName("Test loanOutBook Method")
    void loanOutBookEdgeCase() {
        //five books were created and loaned out 6 copies
        //The last method call should return a "Book taken"
        LibraryHelperMethods.loanOutBook(INVERTEBRATE_PALEONTOLOGY, 123);
        LibraryHelperMethods.loanOutBook(INVERTEBRATE_PALEONTOLOGY, 124);
        LibraryHelperMethods.loanOutBook(INVERTEBRATE_PALEONTOLOGY, 143);
        LibraryHelperMethods.loanOutBook(INVERTEBRATE_PALEONTOLOGY, 143);
        LibraryHelperMethods.loanOutBook(INVERTEBRATE_PALEONTOLOGY, 432);
        String actual = LibraryHelperMethods.loanOutBook(INVERTEBRATE_PALEONTOLOGY, 126);
        String expected = "Book taken";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test the addBook Method for returned books")
    void addBook() {

        Collection<Book> books = LibraryHelperMethods.addBook(INVERTEBRATE_PALEONTOLOGY,2, "2nd Edition");

        assertAll(
                ()-> assertEquals(2, books.size(), "Method should create and return 2 copies of book"),
                ()-> assertEquals(7, INVERTEBRATE_PALEONTOLOGY.getTotalQuantity(),"Method add the books created to total book quantity")
        );
    }



    @Test
    @DisplayName("Test the addBook Method to ensure it adds books to the listOfBooks collection")
    void testAddBook() {
        MockDataBase.listOfBooks.clear();
        BookCard IGNEOUS_PETROLOGY = new BookCard("IGNEOUS_PETROLOGY", "J.K BARKS", "GLY_IGP",  "GEOLOGY", "description");
        LibraryHelperMethods.addBook(IGNEOUS_PETROLOGY,6, "2nd Edition");
        LibraryHelperMethods.addBook(INVERTEBRATE_PALEONTOLOGY,4, "2nd Edition");
        int expected = 10;
        int actual = MockDataBase.listOfBooks.size();
        assertEquals(expected, actual, "Method should create and return 2 copies of book");
    }

    @Test
    @DisplayName("Test createBookCard method")
    void createBookCard(){
        LibraryHelperMethods.createBookCard("IGNEOUS_PETROLOGY", "J.K BARKS", "GLY_IGP",  "GEOLOGY", "description");
        LibraryHelperMethods.createBookCard("STRUCTURAL GEOLOGY", "Prof. Imoof", "GLY_STR",  "GEOLOGY", "description");
       BookCard STRUCTURAL_GEOLOGY =  LibraryHelperMethods.createBookCard("STRATIGRAPHY", "Prof. Imoof", "GLY_STRA",  "GEOLOGY", "description");

       assertAll(
               ()->assertEquals(3, MockDataBase.listOfBookCard.size(), "Method should add book card to the listOfBookCard collection"),
               ()-> assertTrue(STRUCTURAL_GEOLOGY instanceof BookCard, "Method should return a BookCard object" )
       );
    }


    @Test
    @DisplayName("Test createBookCard method for duplicate bookCards ")
    void createBookCardForDuplicateEntry(){
        LibraryHelperMethods.createBookCard("IGNEOUS_PETROLOGY", "J.K BARKS", "GLY_IGP",  "GEOLOGY", "description");
        LibraryHelperMethods.createBookCard("STRUCTURAL GEOLOGY", "Prof. Imoof", "GLY_STR",  "GEOLOGY", "description");
        LibraryHelperMethods.createBookCard("STRUCTURAL GEOLOGY", "Prof. Imoof", "GLY_STR",  "GEOLOGY", "description");
        assertEquals(2, MockDataBase.listOfBookCard.size(), "Method should add only unique book card to the listOfBookCard collection");
    }

    @Test
    @DisplayName("Test testProcessRequestsByPriorityOrder")
    void testProcessRequestsByPriorityOrder(){
        //Create book cards with all book information and record
        BookCard PHYSICS = LibraryHelperMethods.createBookCard("Physics", "Steve", "PHY",  "Science", "description");
        BookCard CHEMISTRY = LibraryHelperMethods.createBookCard("Chemistry", "John", "CHM",  "Science", "description");

        //Add 5 of each book to the library shelf
        LibraryHelperMethods.addBook(PHYSICS, 5, "1st Edition");
        LibraryHelperMethods.addBook(CHEMISTRY, 5, "1st Edition");

        //Take orders for books
        LibraryHelperMethods.takeOrder(seniorStudent2, "PHY");
        LibraryHelperMethods.takeOrder(seniorStudent1, "PHY");
        LibraryHelperMethods.takeOrder(juniorStudent2, "PHY");
        LibraryHelperMethods.takeOrder(juniorStudent, "CHM");
        LibraryHelperMethods.takeOrder(juniorTeacher, "CHM");
        LibraryHelperMethods.takeOrder(headTeacher, "PHY");

        //process request others
        LibraryHelperMethods.processRequestsByPriorityOrder();
        assertEquals(6, MockDataBase.records.size(), "Method should create a record add the processed request to records");

    }

    @Test
    @DisplayName("Test testProcessRequestsByFifoOrder")
    void testProcessRequestsByFifoOrder(){
        //Create book cards with all book information and record
        BookCard PHYSICS = LibraryHelperMethods.createBookCard("Physics", "Steve", "PHY",  "Science", "description");
        BookCard CHEMISTRY = LibraryHelperMethods.createBookCard("Chemistry", "John", "CHM",  "Science", "description");

        //Add 5 of each book to the library shelf
        LibraryHelperMethods.addBook(PHYSICS, 5, "1st Edition");
        LibraryHelperMethods.addBook(CHEMISTRY, 5, "1st Edition");

        //Take orders for books
        LibraryHelperMethods.takeOrder(seniorStudent2, "PHY");
        LibraryHelperMethods.takeOrder(seniorStudent1, "PHY");
        LibraryHelperMethods.takeOrder(juniorStudent2, "PHY");
        LibraryHelperMethods.takeOrder(juniorStudent, "CHM");
        LibraryHelperMethods.takeOrder(juniorTeacher, "CHM");
        LibraryHelperMethods.takeOrder(headTeacher, "PHY");

        //process request others
        LibraryHelperMethods.processRequestsByFifoOrder();
        assertEquals(6, MockDataBase.records.size(), "Method should create a record add the processed request to records");


    }

}