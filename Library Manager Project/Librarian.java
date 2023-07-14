// Zach Sally
// April 10, 2022

// Librarian subclass of User that acts as administrator
// Can look up student information, has admin password priveleges, and can
// view all transactions made on the system

public class Librarian extends User 
{    
    public Librarian(int id, String name, String password) 
    {
        super(id, name, password);
    }
    
    public void printLibrarianInfo() 
    {
        System.out.println("Librarian Info: ");
        printUserInfo();
    }
    
    public void printStudentInfo(Student student) 
    {
        if (student == null) 
        {
            System.out.println("Student DNE\n");
            return;
        }

        student.printStudentInfo();
        student.printBooksBorrowed();
    }  
    
    public void printAllTransactions() 
    {
        Library.printAllTransactions();
    }
}
