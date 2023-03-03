// Zach Sally
// April 10, 2022

// Book class defines a book object
// Book has an id, title, author, and description/summary

package librarymanagementtester;

public class Book {
    private int id;
    private String title, author;
    private String summary;
    
    // Constructor sets the information with given parameters
    public Book (int id, String title, String author, String summary) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.summary = summary;
    }
    
    public int getID() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getSummary() {
        return summary;
    }
    
    // Prints out each field of the specific book
    public void printBookInfo() {
        
        System.out.println("Book ID: " + id);
        System.out.println("Book Title: " + title);
        System.out.println("Book Author: " + author);
        System.out.println("Book Summary: " + summary);
        System.out.println();
    }
}
