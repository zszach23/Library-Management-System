// Zach Sally
// April 10, 2022

// Library class holds all the books and transactions in the system
// bookShelf stores all available books in library, and allTransactions
// stores every transaction made on the system for all students

import java.util.*;

public class Library 
{
    public static final int NUM_BOOKS = 100;

    private static Book[] bookShelf = new Book[NUM_BOOKS];
    private static ArrayList<Transaction> allTransactions = new ArrayList<Transaction>();

    public static void initializeBookShelf()
    {
        for (int book = 0; book < Library.NUM_BOOKS; book++) 
        {
            String bookTitle = "Book " + book;
            String bookAuthor = "Book " + book + " Author";
            String bookSummary = "Book " + book + " is found at local library";
            Library.addBooktoShelf(new Book(book, bookTitle, bookAuthor, bookSummary));
        }
    }

    public static Book findBookinLibrary(int bookID) 
    {
        return bookShelf[bookID];
    }
    
    public static void addBooktoShelf(Book book) 
    {
        bookShelf[book.getID()] = book;
    }
    
    public static void removeBookFromShelf(Book book) 
    {
        bookShelf[book.getID()] = null;
    }
    
    public static Book[] getBookShelf() 
    {
        return bookShelf;
    }
    
    public static void printBookShelf() 
    {
        for (int book = 0; book < bookShelf.length; book++) 
        {
            if (bookShelf[book] != null) 
            {
                bookShelf[book].printBookInfo();
            }
        }
    }
    
    public static void addTransaction(Book book, String type) 
    {
        allTransactions.add(new Transaction(book, type));
    }
    
    public static void printAllTransactions() 
    {
        
        if (allTransactions.size() == 0) 
        {
            System.out.println("No Transactions Have Been Made\n");
            return;
        }
        
        for (int transaction = 0; transaction < allTransactions.size(); transaction++) 
        {
            System.out.println("Transaction Number: " + transaction);
            allTransactions.get(transaction).printTransaction();
        }
    }
}
