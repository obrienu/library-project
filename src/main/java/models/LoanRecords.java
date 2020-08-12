package models;

import java.time.LocalDate;

public class LoanRecords {
    private long libraryId;
    private String bookCode;
    private long bookId;
    private LocalDate dateBorrowed  = LocalDate.now();
    private LocalDate dateDueForReturn = LocalDate.now().plusDays(14);
    private boolean isReturned = false;


    public LoanRecords() {
    }

    /**
     *
     * @param libraryId
     * @param bookCode
     * @param bookId
     */
    public LoanRecords(long libraryId, String bookCode, long bookId) {
         this.libraryId = libraryId;
        this.bookCode = bookCode;
        this.bookId = bookId;
    }

    public long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(long libraryId) {
        this.libraryId = libraryId;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getDateBorrowed() {
        return dateBorrowed.toString();
    }

    public String getDateDueForReturn() {
        return dateDueForReturn.toString();
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    @Override
    public String toString() {
        return "LoanRecords{" +
                "libraryId=" + libraryId +
                ", bookCode='" + bookCode + '\'' +
                ", bookId=" + bookId +
                ", dateBorrowed=" + dateBorrowed +
                ", dateDueForReturn=" + dateDueForReturn +
                ", isReturned=" + isReturned +
                '}';
    }
}
