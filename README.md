# Library-Management-System

Contains the source code for a multi file object oriented project that simulates a library management system.

To run:
```
javac LibraryManagementTester.java

java LibraryManagementTester
```

### Files

* **Management System** acts as a virtual menu system that can be logged in by students and librarian. The user can also change and view settings.

* **Users**

  * *Students* can create and log into an account based on ID and password. They can borrow and return books.

  * *Librarians* have admin priveleges that allow them to view the student accounts, remaining library books, and view and authorize student transactions.

* **Library** holds all books in the library and all transactions made with the system.

* **Book** holds the information of a book 

* **Transaction** holds the information of transactions
