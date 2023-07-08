// Zach Sally
// April 10, 2022

// Librarian subclass of User that acts as administrator
// Can look up student information, has admin password priveleges, and can
// view all transactions made on the system

public class Librarian extends User {
    private String department;
    
    // Constructor takes in user parameters and department
    public Librarian(int id, String name, String password, String department) {
        super(id, name, password);
        this.department = department;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void printLibrarianInfo() {
        System.out.println("Librarian Info: ");
        printUserInfo();
    }
    
    // Prints the given student's info
    public void printStudentInfo(Student s) {
        if (s == null) {
            System.out.println("Student DNE\n");
            return;
        }
        s.printStudentInfo();
        s.printBooksBorrowed();
    }  
    
    // Prints every transaction made in the library system
    public void printAllTransactions(Library lib) {
        lib.printAllTransactions();
    }
}
