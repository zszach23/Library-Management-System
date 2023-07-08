// Zach Sally
// April 10, 2022

// Library class holds all the books and transactions in the system
// bookShelf stores all available books in library, and allTransactions
// stores every transaction made on the system for all students

import java.util.*;

public class Library {
    private Book[] bookShelf;
    private ArrayList<Transaction> allTransactions;
    
    // No-arg constructor that intializes the bookshelf to store 100 Books
    // and a list of transactions
    public Library() {
        bookShelf = new Book[100];
        allTransactions = new ArrayList<Transaction>();
    }
    
    // Adds the given book back into the bookShelf array at the index of bookID
    public void addBooktoShelf(Book b) {
        bookShelf[b.getID()] = b;
    }
    
    // Removes the given book from the bookShelf and sets value to null
    // to represent emptyness in that index (bookID)
    public void removeBookFromShelf(Book b) {
        bookShelf[b.getID()] = null;
    }
    
    public Book[] getBookShelf() {
        return bookShelf;
    }
    
    // Prints every book currently available in the library
    public void printBookShelf() {
        for (int i = 0; i < bookShelf.length; i++) {
            if (bookShelf[i] != null) {
                bookShelf[i].printBookInfo();
            }
        }
    }
    
    // Adds a transaction to the library system
    public void addTransaction(Book b, String type) {
        allTransactions.add(new Transaction(b, type));
    }
    
    // Prints every transaction made on the system
    public void printAllTransactions() {
        
        // Check if any transactions have been made in the library
        if (allTransactions.size() == 0) {
            System.out.println("No Transactions Have Been Made\n");
            return;
        }
        
        // Print each transaction in order of date created
        for (int i = 0; i < allTransactions.size(); i++) {
            System.out.println("Transaction Number: " + i);
            allTransactions.get(i).printTransaction();
        }
    }
}
