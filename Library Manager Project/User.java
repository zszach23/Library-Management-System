// Zach Sally
// April 10, 2022

// User class acts a superclass that stores an id, name and password
// for Student and Librarian classes

package librarymanagementtester;

public class User extends Library {
    private int id;
    private String name;
    private String password;
    
    // Constructor initializes id, name, and password to given parameter values
    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    
    public int getID() {
        return id;
    } 
    
    public String getName() {
        return name;
    }
    
    public String getPassword() {
        return password;
    }
    
    // Sets the user's password to the given password
    public void setPassword(String s) {
        password = s;
    }
    
    public void printUserInfo() {
        System.out.println("User ID: " + getID());
        System.out.println("Name: " + getName());
        System.out.println("Password: " + getPassword());
    }
    
}
