// Zach Sally
// April 10, 2022

// Tests the Library System

import java.util.*;

public class LibraryManagementTester 
{
    public static final int LIBRARIAN_ID = 100;
    public static final int NUM_STUDENTS = 100;

    public static final int STUDENT_LOGIN = 1;
    public static final int LIBRARIAN_LOGIN = 2;
    public static final int CREATE_NEW_STUDENT = 3;

    public static int numActiveStudents = 0;
    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        Student[] students = new Student[NUM_STUDENTS];
        Librarian librarian;
        
        int login, selection;
        int studentID;
        String studentName = "", studentPassword = "";
        String librarianName = "", librarianPassword = "";

        int bookID;
        Book bookSelection;
        String password;
        Student s;

        Library.initializeBookShelf();
        
        System.out.println("Set up Librarian Account:");
        createUser(librarianName, librarianPassword);
        librarian = new Librarian(LIBRARIAN_ID, librarianName, librarianPassword);
        
        while (true) 
        {            
            printMainMenu();
            
            login = scan.nextInt();
            
            // Login as a student
            if (login == STUDENT_LOGIN) 
            {                
                System.out.print("Enter Your ID: ");
                studentID = scan.nextInt();
                
                System.out.print("Enter Your Password: ");
                studentPassword = scan.next();

                // Checks if given ID is valid and if password matches
                if (studentID < numActiveStudents && studentID >= 0 && students[studentID] != null
                    && students[studentID].getPassword().equals(studentPassword)) 
                {
                    
                    System.out.println("Login Successful\n");
                } 
                else 
                {
                    
                    System.out.println("Error: Invalid ID or Password");
                    System.out.println("Returning to Main Menu...\n");
                    continue;
                }   
            } 
            else if (login == LIBRARIAN_LOGIN) 
            {
                System.out.print("Enter Admin Password:");
                password = scan.next();
                
                if (librarian.getPassword().equals(password)) 
                {
                    System.out.println("Login Successful\n");
                }
                else 
                {
                    System.out.println("Error: Invalid Password");
                    System.out.println("Returning to Main Menu...\n");
                    continue;
                }
                
                printLibrarianMenu();
                
                while ((selection = scan.nextInt()) != 4) 
                {
                    switch (selection) 
                    {                        
                        case 1: 
                            
                            System.out.println("Enter Student ID");
                            studentID = scan.nextInt();
                            
                            if (studentID < 0 || studentID > students.length) 
                            {
                                System.out.println("Invalid Student ID");
                                break;
                            }
                            
                            s = students[studentID];
                            librarian.printStudentInfo(s);
                            break;
                        
                        // Librarian prints every transaction made on the system
                        case 2:
                            
                            Library.printAllTransactions();
                            break;
                        
                        // View Librarian Information and Settings
                        case 3: 
                            
                            // Prints librarian information and prompts
                            librarian.printLibrarianInfo();
                            printSettingsMenu();
                            
                            selection = scan.nextInt();
                            
                            // Librarian changes password
                            if (selection == 1) 
                            {                                
                                // Verify current password
                                System.out.print("Enter the current password: ");
                                password = scan.next();
                                
                                // If valid password input, set password to new password
                                if (librarian.verifyPassword(password)) 
                                {
                                    System.out.print("Enter new password: ");
                                    password = scan.next();
                                    librarian.setPassword(password);
                                }
                            } 
                            else 
                            {
                                System.out.println("Returning to Librarian Menu...\n");
                            }

                        case 4:
                            break;
                            
                        default :
                            System.out.println("Invalid Selection\n");
                    }
                    
                    printLibrarianMenu();
                }
                
                continue;
                
            } 
            else if (login == CREATE_NEW_STUDENT) 
            {
                // Prompts user for name and password
                System.out.print("Enter Your Name: ");
                studentName = scan.next();
                
                System.out.print("Enter a password: ");
                studentPassword = scan.next();
                
                // Creates a new student with next available ID and given information
                students[numActiveStudents] = new Student(numActiveStudents, studentName, studentPassword);
                studentID = numActiveStudents;
                numActiveStudents++;
                
                // Confirm to user that account creation successful
                System.out.println("\nNew Student Account Succesfully Created: ");
                System.out.println("Name: " + studentName);
                System.out.println("ID: " + studentID);
                System.out.println("Password: " + studentPassword);
                System.out.println();  
            } 
            else 
            {
                System.out.println("Invalid Selection");
                System.out.println("Returning to Main Menu...\n");
                continue;
            }

            printStudentMenu();
            
            while ((selection = scan.nextInt()) != 7) 
            {
                switch (selection) 
                {
                    // Browses all available books in the library
                    case 1: 
                        Library.printBookShelf();
                        break;
                    
                    // Prints out each book that the current student is borrowing    
                    case 2:
                        students[studentID].printBooksBorrowed();
                        break;
                    
                    // Borrows a selected book    
                    case 3:
                        
                        // Checks if student is currently borrowing the max amount of books
                        if (students[studentID].getBooksBorrowed().size() >= 3) 
                        {
                            System.out.println("You have the maximum number of books borrowed, "
                                               + "please return at least one of the following to borrow this book: ");
                            students[studentID].printBooksBorrowed();
                            break;
                        }
                        
                        // Prompt user to enter book ID and search for book in library
                        System.out.print("Enter the Book ID of Borrowing Book: ");
                        bookID = scan.nextInt();
                        bookSelection = Library.findBookinLibrary(bookID);
                        
                        // Book was not found in library if the selection is null
                        if (bookSelection == null) 
                        {
                            System.out.println("Book not found in library\n");
                            break;
                        }
                        
                        // Prompts admin (librarian) verification by password input
                        System.out.print("Enter Admin Password to Verify: ");
                        password = scan.next();
                        
                        // If valid verification, student borrows book and book
                        // at index is removed from library
                        if (librarian.verifyPassword(password)) 
                        {
                            students[studentID].borrowBook(bookSelection);
                            Library.removeBookFromShelf(bookSelection);
                            
                        // Invalid admin verification    
                        } 
                        else 
                        {
                            System.out.println("Invalid Verification");
                            System.out.println("Returning to Student Menu...\n");
                        }

                        break;
                    
                    // Student returns a book they are current borrowing
                    case 4:
                        
                        // Prompt student to enter book ID and find if in account
                        System.out.print("Enter Book ID of Returning Book: ");
                        bookID = scan.nextInt();
                        
                        bookSelection = students[studentID].findBookinAccount(bookID);
                        
                        // Selection was not found in students account (not 
                        // current borrowing book)
                        if (bookSelection == null) 
                        {
                            System.out.println("Book not found in account\n");
                            break;
                        }
                        
                        // Admin verification of transaction
                        System.out.print("Enter Admin Password to Verify: ");
                        password = scan.next();
                        
                        // If verification successful, student returns the book
                        // to the library
                        if (librarian.verifyPassword(password)) 
                        {
                            students[studentID].returnBook(bookSelection);
                            System.out.println("\nSuccessfully Returned Book: ");
                            bookSelection.printBookInfo();
                            
                        // Invalid admin verification    
                        } 
                        else 
                        {
                            System.out.println("Invalid Verification");
                            System.out.println("Returning to Student Menu...\n");
                        }

                        break;
                    
                    // Prints the student's personal transaction history with library
                    case 5:
                        students[studentID].printTransactionHistory();
                        break;
                        
                    // Displays account details and option to change password    
                    case 6: 
                        
                        // Displays student info and settings menu options
                        students[studentID].printStudentInfo();
                        printSettingsMenu();
                        
                        selection = scan.nextInt();
                        
                        // Student selects to change their password
                        if (selection == 1) 
                        {                            
                            // Verify with the current password
                            System.out.print("Enter current password: ");
                            password = scan.next();
                            
                            // If valid, student enters and sets password to new
                            // password
                            if (students[studentID].getPassword().equals(password)) 
                            {
                                System.out.print("Enter new password: ");
                                password = scan.next();
                                students[studentID].setPassword(password);
                            }
                        
                        // Exit from settings menu
                        } 
                        else 
                        {
                            System.out.println("Returning to Student Menu...\n");
                        }  

                        break;
                        
                    case 7:
                        break;
                        
                    default:
                        System.out.println("Invalid Selection\n");
                }
                
                printStudentMenu();
            }
            
            System.out.println("Successfully Logged Out\n");
        }
    }

    public static void createUser(String name, String password)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Name: ");
        name = scan.nextLine();
        
        System.out.print("Enter Password: ");
        password = scan.nextLine();

        scan.close();
    }

    public static void printMainMenu()
    {
        System.out.println("\nMain Menu");
        System.out.println("1: Login (Student)");
        System.out.println("2: Login (Librarian)");
        System.out.println("3: Create New Account (Student)\n");
    }
    
    public static void printLibrarianMenu() 
    {
        System.out.println("Librarian Account Menu");
        System.out.println("1. Get Student Info");
        System.out.println("2. View All Transactions");
        System.out.println("3. Account Details and Settings");
        System.out.println("4. Log Out\n");
    }
    
    public static void printStudentMenu() 
    {
        System.out.println("Student Account Menu");
            System.out.println("1. Browse Books");
            System.out.println("2. Books Currently Borrowing");
            System.out.println("3. Borrow a Book (max 3)");
            System.out.println("4. Return a Book");
            System.out.println("5. Transaction History");
            System.out.println("6. Account Details and Settings");
            System.out.println("7. Log Out\n");
    }
    
    public static void printSettingsMenu() 
    {
        System.out.println("Details and Settings: ");
        System.out.println("1. Change Password");
        System.out.println("2. Exit");
        System.out.println();
    }
}
