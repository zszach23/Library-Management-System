// Zach Sally
// April 10, 2022

// Student is a subclass of User that defines a student using the library system
// Can browse, borrow, and return books in library and account, check their 
// personal transaction history, and view details and change password

import java.util.*;

public class Student extends User 
{
    private ArrayList<Book> booksBorrowed;
    private ArrayList<Transaction> personalTransactions;
    
    public Student(int id, String name, String password) 
    {
        super(id, name, password);
        this.booksBorrowed = new ArrayList<Book>();
        this.personalTransactions = new ArrayList<Transaction>();
    }
    
    public ArrayList<Book> getBooksBorrowed() 
    {
        return this.booksBorrowed;
    }
    
    public void printStudentInfo() 
    {
        System.out.println("Student Information: ");
        printUserInfo();
    }

    public Book findBookinAccount(int bookID) 
    {
        for (int borrowedBook = 0; borrowedBook < this.booksBorrowed.size(); borrowedBook++) 
        {
            if (this.booksBorrowed.get(borrowedBook).getID() == bookID) 
            {
                return this.booksBorrowed.get(borrowedBook);
            }
        }

        return null;
    }
    
    public void borrowBook(Book book) 
    {        
        if (book.getID() < 0 || book.getID() >= Library.getBookShelf().length) 
        {
            System.out.println("Invalid Book ID");
        } 
        else if (Library.getBookShelf()[book.getID()] == null) 
        {
            System.out.println("Book not in stock");
        
        } 
        else 
        {
            booksBorrowed.add(book);
            personalTransactions.add(new Transaction(book, "Borrow"));
            Library.addTransaction(book, "Borrow");

            System.out.println("\nTransaction Successful");
            System.out.println("You are now borrowing: ");
            book.printBookInfo();
        }
    }

    public void returnBook(Book book) 
    {
        this.booksBorrowed.remove(book);
        Library.addBooktoShelf(book);

        this.personalTransactions.add(new Transaction(book, "Return"));
        Library.addTransaction(book, "Return");
    }
    
    public void printBooksBorrowed() 
    {        
        if (booksBorrowed.size() == 0) 
        {
            System.out.println("Not Borrowing Any Books\n");
            return;
        }
        
        for (int borrowedBook = 0; borrowedBook < booksBorrowed.size(); borrowedBook++) 
        {
            booksBorrowed.get(borrowedBook).printBookInfo();
            System.out.println();
        }
    }
    
   public void printTransactionHistory() 
   {
       System.out.println("User: " + getName() + "\n");
       
       if (personalTransactions.size() == 0) 
       {
           System.out.println("No Previous Transactions\n");
           return;
       }
       
       System.out.println("Transactions: " + personalTransactions.size() + "\n");
       for (int transaction = 0; transaction < personalTransactions.size(); transaction++) 
       {
           System.out.println("Transaction " + transaction);
           personalTransactions.get(transaction).printTransaction();
       }
   }
}
