import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private final int publicationYear;

    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }

    public String getTitle() {
        return title;
    }


    public String getIsbn() {
        return isbn;
    }


    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Year: " + publicationYear;
    }
}


class Library {
    private final List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }


    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added to the library: " + book.getTitle());
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("The library is empty.");
        } else {
            System.out.println("Library Books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }



    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public boolean removeBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                books.remove(book);
                System.out.println("Book removed from the library: " + book.getTitle());
                return true;
            }
        }
        System.out.println("Book with ISBN " + isbn + " not found in the library.");
        return false;
    }
}



public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add a Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Find a Book by Title");
            System.out.println("4. Remove a Book by ISBN");
            System.out.println("5. Exit");

            int choice = getMenuChoice(scanner);

            switch (choice) {
                case 1 -> addBookToLibrary(scanner, library);
                case 2 -> library.displayBooks();
                case 3 -> findBookByTitle(scanner, library);
                case 4 -> removeBookByIsbn(scanner, library);
                case 5 -> {
                    System.out.println("Exiting Library Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }

        }
    }

    private static int getMenuChoice(Scanner scanner) {
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }



    private static void addBookToLibrary(Scanner scanner, Library library) {
        scanner.nextLine();
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter publication year: ");
        int year = scanner.nextInt();
        Book newBook = new Book(title, author, isbn, year);
        library.addBook(newBook);
    }

    private static void findBookByTitle(Scanner scanner, Library library) {
        scanner.nextLine();
        System.out.print("Enter book title to search: ");
        String searchTitle = scanner.nextLine();
        Book foundBook = library.findBookByTitle(searchTitle);
        if (foundBook != null) {
            System.out.println("Book found:\n" + foundBook);
        } else {
            System.out.println("Book not found.");
        }
    }



    private static void removeBookByIsbn(Scanner scanner, Library library) {
        scanner.nextLine();
        System.out.print("Enter ISBN to remove a book: ");
        String removeIsbn = scanner.nextLine();
        library.removeBookByIsbn(removeIsbn);
    }
}
