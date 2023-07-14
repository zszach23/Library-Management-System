// Zach Sally
// April 10, 2022

// Transaction class that defines a transaction made by a student when borrowing
// or returning a book
// Transaction holds an id value, a book, the date created, and what type
// of transaction was made

import java.util.*;

public class Transaction 
{
    private int id;
    private Book book;
    private Date date;
    private String type;
    
    public Transaction(Book book, String type) 
    {
        this.book = book;
        this.date = new Date();
        this.type = type;
    }
    
    public int getTransactionID() 
    {
        return id;
    }
    
     public void printTransaction() 
     {
        System.out.println("Transaction Date: " + this.date);
        System.out.println("Type: " + this.type);
        this.book.printBookInfo();
    }
}
