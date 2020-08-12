package library_utils;

import models.*;

import java.util.*;


public class LibraryHelperMethods {


    public static void takeOrder(User user, String bookCode){
        MockDataBase.PRIORITY_ORDERS.add(new Order(user, bookCode));
        MockDataBase.FIFO_ORDERS.add(new Order(user, bookCode));
    }

    /**
     * Method creates book card and adds the card to the listOfBookCards
     * in the MockUpDataBase
     * @param title
     * @param author
     * @param bookCode
     * @param category
     * @param description
     * @return
     */
    public static BookCard createBookCard(String title, String author, String bookCode, String category, String description){
        try{
            BookCard newBookCard = new BookCard( title,  author,  bookCode,  category,  description);
            MockDataBase.listOfBookCard.put(bookCode, newBookCard);
            return newBookCard;
        }catch (Exception e) {
            System.out.println("An error occured while trying to create book card");
            e.printStackTrace();
        }
        return null;
    }




    /**
     * Method creates books based of the BookCard information
     * It adds the books to the listOfBook in the MockUpDataBase
     * @param quantity Quantities of books to be generated
     * @param edition the book edition
     * @return
     */
    public static List<Book> addBook(BookCard bookCard, int quantity, String edition){
        List<Book> books = new ArrayList<>();
        try{
            long bookId;
            long ID_OF_LAST_BOOK_ADDED = bookCard.getID_OF_LAST_BOOK_ADDED();

            for (int i = 0; i < quantity; i++) {
                //generates unique book id based of the id of the last added book;
                bookId = ++ID_OF_LAST_BOOK_ADDED;
                books.add(new Book(bookCard.getTitle(), bookCard.getAuthor(), bookCard.getBookCode(), bookId, edition));
            }

            bookCard.setID_OF_LAST_BOOK_ADDED(ID_OF_LAST_BOOK_ADDED);
            //increments the total available book and books in shelf by the quantity of books added
            bookCard.setTotalQuantity(bookCard.getTotalQuantity() + quantity);
            bookCard.setAvailableQuantityInShelf(bookCard.getAvailableQuantityInShelf() + quantity);
            //adds book to Mock-up database
            MockDataBase.listOfBooks.addAll(books);
            return books;
        }catch (Exception e){
            System.out.println("An error occured while trying to add books");
            e.printStackTrace();
        }
        return books;
    }


    /**
     * Method gets only available book from the books list based of its book code and its isInShelf fields
     * and its isInShelf field
     * @param bookCode
     * @return
     */
    public static Book getBookAvailableFromShelf(String bookCode){
        for(Book book: MockDataBase.listOfBooks){
            if(bookCode.equals(book.getBookCode()) && book.isInShelf()){
                return book;
            }
        }
        return null;
    }

    /**
     * Method gets a particular book based of its Book code and book Id
     * @param bookCode
     * @param bookId
     * @return
     */
    public static Book getBook(String bookCode, long bookId){
        for(Book book: MockDataBase.listOfBooks){
            if(bookCode.equals(book.getBookCode()) && book.getBookId() == bookId){
                return book;
            }
        }
        return null;
    }

    /**
     * Method decrements books in shelf by one when a book is loaned out.
     * creates a loan record and sends the
     * @param bookCard
     * @param usersLibraryId
     * @return
     */
    public static String loanOutBook(BookCard bookCard, long usersLibraryId) {
        LoanRecords record;
        //Checks for book availability
        if(bookCard.getAvailableQuantityInShelf() == 0){
            return "Book taken";
        }
        //gets the available copy of the book and sets its inShelf status to false
        Book book = getBookAvailableFromShelf(bookCard.getBookCode());
        book.setInShelf(false);
        //decrease the books in shelf record by 1
        bookCard.setAvailableQuantityInShelf(bookCard.getAvailableQuantityInShelf() - 1);
        record = new LoanRecords(usersLibraryId, book.getBookCode(), book.getBookId());
        MockDataBase.records.add(record);
        return "Please take your book";
    }

    /**
     * Method gets the bookCard based off the book code
     * @param bookCode
     * @return
     */
    public static BookCard getBookCard(String bookCode){
        return MockDataBase.listOfBookCard.get(bookCode);
    }

    /**
     * Method gets the record from the records list based of the userid, bookCode and bookId
     * @param userLibraryId
     * @param bookCode
     * @param bookId
     * @return
     */
    protected static LoanRecords getRecord( long userLibraryId, String bookCode, long bookId){
        for(LoanRecords record : MockDataBase.records){
            if(bookCode.equals(record.getBookCode())
                    && record.getBookId() == bookId
                    && record.getLibraryId() == userLibraryId){
                return record;
            }
        }
        return null;
    }


    /**
     * Method gets the record for the loan from the records list sets its status to returned
     * get the book with the bookId and bookCode and sets its availability status back to true
     * and increments the quantity of available books by 1
     * @param bookCode
     * @param bookId
     * @param userLibraryId
     * @return
     * @throws Exception
     */
    public static boolean returnBook(String bookCode, long bookId, long userLibraryId) throws Exception {
        Book book = null;
        BookCard bookCard = null;
        LoanRecords record = getRecord(userLibraryId, bookCode, bookId);
        if(record != null){

            book = getBook(bookCode, bookId);
            bookCard = getBookCard(bookCode);
        }

        if(bookCard!= null && bookCard.getAvailableQuantityInShelf() == bookCard.getTotalQuantity()){
            throw new Exception("Quantity on Shelf cannot exceed the total number of book");
        }

        if( book != null && bookCard != null){
            book.setInShelf(true);
            bookCard.setAvailableQuantityInShelf(bookCard.getAvailableQuantityInShelf() + 1);
            record.setReturned(true);
            return true;
        }
        return false;
    }


    /**
     * Method processes orders from the priority List
     * NOTE FIRST IMPLEMENTATION
     */
    public static void processRequestsByPriorityOrder(){
        Order order;
        User user;
        BookCard bookCard;

        try{
            while(!MockDataBase.PRIORITY_ORDERS.isEmpty()){
                order = MockDataBase.PRIORITY_ORDERS.remove();
                user = order.getUser();
                bookCard = getBookCard(order.getBookCode());
                //checks to ensure book is available on the shelf
                if(bookCard.getAvailableQuantityInShelf() > 0){
                    loanOutBook(bookCard, user.getLibraryId());
                }else{
                    System.out.println("Book is already taken");
                }
            }
        }catch (Exception e){
            System.out.println("An Error Occured while trying to process request");
            e.printStackTrace();
        }
    }

    /**
     * Method processes orders from the fifo List
     * NOTE SECOND IMPLEMENTATION
     */
    public static void processRequestsByFifoOrder(){
        Order order;
        User user;
        BookCard bookCard;

        try{
            while(!MockDataBase.FIFO_ORDERS.isEmpty()){
                order = MockDataBase.FIFO_ORDERS.remove();
                user = order.getUser();
                bookCard = getBookCard(order.getBookCode());
                //checks to ensure book is available on the shelf
                if(bookCard.getAvailableQuantityInShelf() > 0){
                    loanOutBook(bookCard, user.getLibraryId());
                }else{
                    System.out.println("Book is already taken");
                }
            }
        }catch (Exception e){
            System.out.println("An Error occurred while trying to process your request");
            e.printStackTrace();
        }
    }




}
