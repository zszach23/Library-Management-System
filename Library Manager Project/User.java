// Zach Sally
// April 10, 2022

// User class acts a superclass that stores an id, name and password
// for Student and Librarian classes

public abstract class User 
{
    private int id;
    private String name;
    private String password;
    
    public User(int id, String name, String password) 
    {
        this.id = id;
        this.name = name;
        this.password = password;
    }
    
    public int getID() 
    {
        return this.id;
    } 
    
    public String getName() 
    {
        return this.name;
    }
    
    public String getPassword() 
    {
        return this.password;
    }
    
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public boolean verifyPassword(String password) 
    {
        return password.equals(this.password);
    }
    
    public void printUserInfo() 
    {
        System.out.println("User ID: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Password: " + this.password);
    }
    
}
