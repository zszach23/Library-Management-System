// Zach Sally
// April 10, 2022

// Student is a subclass of User that defines a student using the library system
// Can browse, borrow, and return books in library and account, check their 
// personal transaction history, and view details and change password

package librarymanagementtester;

import java.util.*;

public class Student extends User {
    private ArrayList<Book> booksBorrowed;
    private ArrayList<Transaction> personalTransactions;
    
    // Constructor takes User parameters and initializes booksBorrowed and
    // personalTransactions fields
    public Student(int id, String name, String password) {
        super(id, name, password);
        booksBorrowed = new ArrayList<Book>();
        personalTransactions = new ArrayList<Transaction>();
    }
    
    public ArrayList<Book> getBooksBorrowed() {
        return booksBorrowed;
    }
    
    // Prints the ID, Name, and Password of the Student
    public void printStudentInfo() {
        System.out.println("Student Information: ");
        printUserInfo();
    }
    
    // Borrows a book from the library and stores it in account (max 3)
    public void borrowBook(Library lib, Book b) {
        
        // Check if the book id is valid
        if (b.getID() < 0 || b.getID() > lib.getBookShelf().length) {
            System.out.println("Invalid Book ID");
            
        // Check if the book is already taken    
        } else if (lib.getBookShelf()[b.getID()] == null) {
            System.out.println("Book not in stock");
        
        // Adds book to booksBorrwoed, updates both transactions, and prints out
        // the book information
        } else {
            booksBorrowed.add(b);
            personalTransactions.add(new Transaction(b, "Borrow"));
            lib.addTransaction(b, "Borrow");
            System.out.println("\nTransaction Successful");
            System.out.println("You are now borrowing: ");
            b.printBookInfo();
        }
        
    }
    
    // Returns the given book back to the library
    // Removes book from booksBorrowed, adds it back to the library, and 
    // updates the transactions
    public void returnBook(Library lib, Book b) {
        booksBorrowed.remove(b);
        lib.addBooktoShelf(b);
        personalTransactions.add(new Transaction(b, "Return"));
        lib.addTransaction(b, "Return");
    }
    
    //Prints out the books the student is currently holding in their account
    public void printBooksBorrowed() {
        
        // Check if student is borrowing any books
        if (booksBorrowed.size() == 0) {
            System.out.println("Not Borrowing Any Books\n");
            return;
        }
        
        for (int i = 0; i < booksBorrowed.size(); i++) {
            booksBorrowed.get(i).printBookInfo();
            System.out.println();
        }
    }
    
    // Prints every transaction the student has made
   public void printTransactionHistory() {
       System.out.println("User: " + getName());
       
       // Check if student has every made a transaction
       if (personalTransactions.size() == 0) {
           System.out.println("No Previous Transactions\n");
           return;
       }
       
       System.out.println("Transactions: " + personalTransactions.size() + "\n");
       for (int i = 0; i < personalTransactions.size(); i++) {
           System.out.println("Transaction " + i);
           personalTransactions.get(i).printTransaction();
       }
   }
}
