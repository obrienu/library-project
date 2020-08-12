package library_utils;

import models.Book;
import models.BookCard;
import models.LoanRecords;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.TreeSet;

public class MockDataBase {
    static MyPriorityList PRIORITY_ORDERS = new MyPriorityList();
    static MyUniqueQueue FIFO_ORDERS  = new MyUniqueQueue();
    static Collection<Book> listOfBooks = new TreeSet<>(new MyBookComparator());
    static HashMap<String, BookCard> listOfBookCard = new HashMap<String, BookCard>();
    static Collection<LoanRecords> records = new ArrayList<>();
}
