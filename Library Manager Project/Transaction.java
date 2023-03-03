// Zach Sally
// April 10, 2022

// Transaction class that defines a transaction made by a student when borrowing
// or returning a book
// Transaction holds an id value, a book, the date created, and what type
// of transaction was made

package librarymanagementtester;

import java.util.*;

public class Transaction {
    private int id;
    private Book book;
    private Date date;
    private String type;
    
    // Constructor sets up book, fills in the date, and the type of transaction
    public Transaction(Book book, String type) {
        this.book = book;
        this.date = new Date();
        this.type = type;
    }
    
    public int getTransactionID() {
        return id;
    }
    
    public Book getBookInfo() {
        return book;
    }
    
    public Date getDateOfTransaction() {
        return date;
    }
    
    // Prints the information involved within a transaction
    // Date, type, and all the book information
     public void printTransaction() {
        System.out.println("Transaction Date: " + date);
        System.out.println("Type: " + type);
        getBookInfo().printBookInfo();
    }
}
