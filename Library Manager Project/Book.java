// Zach Sally
// April 10, 2022

// Book class defines a book object
// Book has an id, title, author, and description/summary

public class Book 
{
    private int id;
    private String title, author;
    private String summary;
    
    public Book (int id, String title, String author, String summary) 
    {
        this.id = id;
        this.title = title;
        this.author = author;
        this.summary = summary;
    }
    
    public int getID() 
    {
        return this.id;
    }   
    
    public void printBookInfo() 
    {
        System.out.println("Book ID: " + this.id);
        System.out.println("Book Title: " + this.title);
        System.out.println("Book Author: " + this.author);
        System.out.println("Book Summary: " + this.summary);
        System.out.println();
    }
}
