package library_utils;

import models.*;

import java.util.*;


public class LibraryHelperMethods {


    public static void takeOrder(User user, String bookCode){
        MockDataBase.PRIORITY_ORDERS.add(new Order(user, bookCode));
        MockDataBase.FIFO_ORDERS.add(new Order(user, bookCode));
    }

    /**
     *
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
                bookId = ++ID_OF_LAST_BOOK_ADDED;
                books.add(new Book(bookCard.getTitle(), bookCard.getAuthor(), bookCard.getBookCode(), bookId, edition));
            }
            bookCard.setID_OF_LAST_BOOK_ADDED(ID_OF_LAST_BOOK_ADDED);
            bookCard.setTotalQuantity(bookCard.getTotalQuantity() + quantity);
            bookCard.setAvailableQuantityInShelf(bookCard.getAvailableQuantityInShelf() + quantity);
            MockDataBase.listOfBooks.addAll(books);
            return books;
        }catch (Exception e){
            System.out.println("An error occured while trying to add books");
            e.printStackTrace();
        }
        return books;
    }



    public static Book getBookAvailableFromShelf(String bookCode){
        for(Book book: MockDataBase.listOfBooks){
            if(bookCode.equals(book.getBookCode()) && book.isInShelf()){
                return book;
            }
        }
        return null;
    }


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
     */
    public static String loanOutBook(BookCard bookCard, long usersLibraryId) {
        LoanRecords record;
        if(bookCard.getAvailableQuantityInShelf() == 0){
            return "Book taken";
        }
        Book book = getBookAvailableFromShelf(bookCard.getBookCode());
        book.setInShelf(false);
        bookCard.setAvailableQuantityInShelf(bookCard.getAvailableQuantityInShelf() - 1);
        record = new LoanRecords(usersLibraryId, book.getBookCode(), book.getBookId());
        MockDataBase.records.add(record);
        return "Please take your book";
    }


    public static BookCard getBookCard(String bookCode){
        return MockDataBase.listOfBookCard.get(bookCode);
    }





    public static void processRequestsByPriorityOrder(){
        Order order;
        User user;
        BookCard bookCard;

        try{
            while(!MockDataBase.PRIORITY_ORDERS.isEmpty()){
                order = MockDataBase.PRIORITY_ORDERS.remove();
                user = order.getUser();
                bookCard = getBookCard(order.getBookCode());
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

    public static void processRequestsByFifoOrder(){
        Order order;
        User user;
        BookCard bookCard;

        try{
            while(!MockDataBase.FIFO_ORDERS.isEmpty()){
                order = MockDataBase.FIFO_ORDERS.remove();
                user = order.getUser();
                bookCard = getBookCard(order.getBookCode());

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

}
