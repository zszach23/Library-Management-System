// Zach Sally
// April 10, 2022

// Tests the Library System

import java.util.*;

public class LibraryManagementTester 
{
    public static final int STUDENT_LOGIN = 1;
    public static final int LIBRARIAN_LOGIN = 2;
    public static final int CREATE_NEW_STUDENT = 3;
    public static final int EXIT_SYSTEM = 4;

    public static final int LIBRARIAN_GET_STUDENT_INFO = 1;
    public static final int LIBRARIAN_PRINT_ALL_TRANSACTIONS = 2;
    public static final int LIBRARIAN_SETTINGS = 3;
    public static final int LIBRARIAN_EXIT = 4;

    public static final int STUDENT_BROWSE_BOOKS = 1;
    public static final int STUDENT_BOOKS_BORROWED = 2;
    public static final int STUDENT_BORROW_BOOK = 3;
    public static final int STUDENT_RETURN_BOOK = 4;
    public static final int STUDENT_PRINT_PERSONAL_TRANSACTIONS = 5;
    public static final int STUDENT_SETTINGS = 6;
    public static final int STUDENT_EXIT = 7;

    public static final int SETTINGS_CHANGE_PASSWORD = 1;

    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);

        initalizeSystem();
        
        while (true) 
        {        
            clearConsole();    
            printMainMenu();
            
            int login = scan.nextInt();

            clearConsole();

            switch (login)
            {
                case STUDENT_LOGIN:

                    System.out.print("Enter Your ID: ");
                    int studentID = scan.nextInt();
                    
                    System.out.print("Enter Your Password: ");
                    String inputPassword = scan.next();

                    if (SystemDatabase.verifyStudentLogin(studentID, inputPassword)) 
                    {
                        useStudentMenu(studentID);
                    } 
                    else 
                    {
                        System.out.println("\nError: Invalid Credentials\n");
                        printConfirmation("Main");
                    }

                    break;
                
                case LIBRARIAN_LOGIN:
                    
                    System.out.print("Enter Admin Password: ");
                    inputPassword = scan.next();
                    
                    if (SystemDatabase.verifyAdminLogin(inputPassword)) 
                    {
                        useLibrarianMenu();
                    }
                    else 
                    {
                        System.out.println("\nError: Invalid Credentials\n");
                        printConfirmation("Main");
                    }   
                    
                    break;

                case CREATE_NEW_STUDENT:

                    System.out.print("Enter Your Name: ");
                    String studentName = scan.next();
                    
                    System.out.print("Enter a password: ");
                    inputPassword = scan.next();

                    studentID = SystemDatabase.createNewStudent(studentName, inputPassword);
                    
                    if (studentID != SystemDatabase.INVALID_ID)
                    {
                        System.out.println("\nNew Student Created!");
                        SystemDatabase.students[studentID].printUserInfo();
                    }
                    else
                    {
                        System.out.println("\nError: Student Capacity Limit Reached. Cannot create new user.\n");
                    }

                    printConfirmation("Main");

                    break;

                case EXIT_SYSTEM:
                    
                    System.out.println("\nExiting System...\n");
                    System.exit(0);
                    break;
                
                default:
                    
                    System.out.println("Invalid Selection");
                    System.out.println("Returning to Main Menu...\n");
                    break;
            }
        }
    }

    public static void initalizeSystem()
    {
        Scanner scan = new Scanner(System.in);

        Library.initializeBookShelf();

        System.out.println("Set up Librarian Account:\n");
        System.out.print("Enter Your Name: ");
        String librarianName = scan.next();
        
        System.out.print("Enter a password: ");
        String librarianPassword = scan.next();

        SystemDatabase.createNewLibrarian(librarianName, librarianPassword);
    }

    public static void useLibrarianMenu()
    {
        Scanner scan = new Scanner(System.in);
        int selection = -1;

        clearConsole();
        printLibrarianMenu();

        while ((selection = scan.nextInt()) != LIBRARIAN_EXIT) 
        {
            clearConsole();

            switch (selection) 
            {                        
                case LIBRARIAN_GET_STUDENT_INFO: 
                    
                    System.out.print("Enter Student ID: ");
                    int studentID = scan.nextInt();
                    
                    if (studentID < 0 || studentID >= SystemDatabase.numActiveStudents)
                    {
                        System.out.println("\nInvalid Student ID\n");
                        break;
                    }
                    
                    SystemDatabase.librarian.printStudentInfo(SystemDatabase.students[studentID]);
                    break;
                
                case LIBRARIAN_PRINT_ALL_TRANSACTIONS:
                    
                    Library.printAllTransactions();
                    break;
                
                case LIBRARIAN_SETTINGS: 
                    
                    SystemDatabase.librarian.printLibrarianInfo();
                    printSettingsMenu();
                    
                    selection = scan.nextInt();
                    
                    if (selection == SETTINGS_CHANGE_PASSWORD) 
                    {                                
                        System.out.print("Enter your current password: ");
                        String inputPassword = scan.next();
                        
                        if (SystemDatabase.librarian.verifyPassword(inputPassword)) 
                        {
                            System.out.print("Enter new password: ");
                            inputPassword = scan.next();
                            SystemDatabase.librarian.setPassword(inputPassword);
                            System.out.println("\nSuccessfully updated password\n");
                        }
                    } 

                    printConfirmation("Librarian");

                // Should never be called
                case LIBRARIAN_EXIT:

                    break;
                    
                default:
                    System.out.println("\nInvalid Selection\n");
            }
        
            printLibrarianMenu();
        } 

        System.out.println("\nSuccessfully Logged Out");
        printConfirmation("Main");
    }

    public static void useStudentMenu(int studentID)
    {
        Scanner scan = new Scanner(System.in);
        int selection = -1;

        clearConsole();
        printStudentMenu();
        while ((selection = scan.nextInt()) != STUDENT_EXIT) 
        {
            clearConsole();

            switch (selection) 
            {
                case STUDENT_BROWSE_BOOKS: 

                    Library.printBookShelf();
                    break;
                
                case STUDENT_BOOKS_BORROWED:

                    SystemDatabase.students[studentID].printBooksBorrowed();
                    break;
                
                case STUDENT_BORROW_BOOK:
                    
                    if (SystemDatabase.students[studentID].getBooksBorrowed().size() >= SystemDatabase.MAX_BOOKS_BORROWED) 
                    {
                        System.out.println("You have the maximum number of books borrowed, "
                                            + "please return at least one of the following to borrow this book: ");
                        SystemDatabase.students[studentID].printBooksBorrowed();
                        break;
                    }
                    
                    System.out.print("Enter the Book ID of Borrowing Book: ");
                    int bookID = scan.nextInt();
                    Book bookSelection = Library.findBookinLibrary(bookID);
                    
                    if (bookSelection == null) 
                    {
                        System.out.println("\nBook not found in library\n");
                        printConfirmation("Student");
                        break;
                    }
                    
                    System.out.print("Enter Admin Password to Verify: ");
                    String inputPassword = scan.next();
                    
                    if (SystemDatabase.librarian.verifyPassword(inputPassword)) 
                    {
                        SystemDatabase.students[studentID].borrowBook(bookSelection);
                        Library.removeBookFromShelf(bookSelection);   
                    } 
                    else 
                    {
                        System.out.println("Invalid Admin Verification");
                        System.out.println("Returning to Student Menu...\n");
                    }

                    break;
                
                case STUDENT_RETURN_BOOK:
                    
                    System.out.print("Enter Book ID of Returning Book: ");
                    bookID = scan.nextInt();
                    
                    bookSelection = SystemDatabase.students[studentID].findBookinAccount(bookID);
                    
                    if (bookSelection == null) 
                    {
                        System.out.println("\nBook not found in account\n");
                        printConfirmation("Student");
                        break;
                    }
                    
                    System.out.print("Enter Admin Password to Verify: ");
                    inputPassword = scan.next();
                    
                    if (SystemDatabase.librarian.verifyPassword(inputPassword)) 
                    {
                        SystemDatabase.students[studentID].returnBook(bookSelection);
                        System.out.println("\nSuccessfully Returned Book: ");
                        bookSelection.printBookInfo();    
                    } 
                    else 
                    {
                        System.out.println("\nInvalid Verification\n");
                    }

                    printConfirmation("Student");

                    break;
                
                case STUDENT_PRINT_PERSONAL_TRANSACTIONS:

                    SystemDatabase.students[studentID].printTransactionHistory();
                    break;
                        
                case STUDENT_SETTINGS: 
                    
                    SystemDatabase.students[studentID].printStudentInfo();
                    printSettingsMenu();
                    
                    selection = scan.nextInt();
                    
                    if (selection == SETTINGS_CHANGE_PASSWORD) 
                    {                            
                        System.out.print("Enter current password: ");
                        inputPassword = scan.next();
                        
                        if (SystemDatabase.students[studentID].getPassword().equals(inputPassword)) 
                        {
                            System.out.print("Enter new password: ");
                            inputPassword = scan.next();
                            SystemDatabase.students[studentID].setPassword(inputPassword);
                        }
                    } 
                    
                    printConfirmation("Student");

                    break;
                    
                case STUDENT_EXIT:
                    // Should not be called
                    break;
                    
                default:
                    System.out.println("Invalid Selection\n");
            }
            
            printStudentMenu();
        }

        System.out.println("\nSuccessfully Logged Out\n");
        printConfirmation("Main");
        
    }

    public static void clearConsole()
    {
        try 
        {
            if (System.getProperty("os.name").contains("Windows")) 
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else 
            {
                System.out.print("\033\143");
            }

        } 
        catch (Exception e) 
        {
            System.out.println("\nOS not compatible with clearScreen()\n");
        }
    }
    
    public static void printMainMenu()
    {
        System.out.println("\nMain Menu");
        System.out.println("1: Login (Student)");
        System.out.println("2: Login (Librarian)");
        System.out.println("3: Create New Account (Student)");
        System.out.println("4. Exit System\n");
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
        System.out.println("3. Borrow a Book (max " + SystemDatabase.MAX_BOOKS_BORROWED + ")");
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

    // User prompted to confirm to return to 'menu'
    public static void printConfirmation(String menu)
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("Type 'exit' to Confirm and Return to the Main Menu: ");
        String input = scan.next();
        while (!input.equals("exit"))
        {
            System.out.print("Confirm by typing 'exit': ");
            input = scan.next();
        }

        System.out.println("Returning to " + menu + " Menu...\n");
    }
}
