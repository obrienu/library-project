package library_utils;

import models.Book;

import java.util.Comparator;

public class MyBookComparator implements Comparator<Book> {
    @Override
    public int compare(Book book1, Book book2) {
        if(book1.getBookCode().equals(book2.getBookCode())){
            if(book1.getBookId() - book2.getBookId() > 0){
                return 1;
            }else if(book1.getBookId() - book2.getBookId() > 0){
                return -1;
            }else return 0;
        }
        return book1.getBookCode().compareTo(book2.getBookCode());
    }
}
