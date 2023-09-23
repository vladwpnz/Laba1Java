# Laba1Java. Опис роботи програми Library Management System

Програма **"Library Management System"** розроблена для управління бібліотекою. 
Вона дозволяє додавати, видаляти та відображати книги в бібліотеці, а також знаходити книгу за її назвою або ISBN. Нижче наведено опис API (Application Programming Interface) для цієї програми, включаючи методи та сутності, а також розглянути "Five Why's" принцип для зрозуміння цілей цього програмного рішення.

## Методи та Сутності:
1. Клас Book: Цей клас представляє книгу та містить основну інформацію про книгу, таку як заголовок, автор, ISBN та рік публікації. Він має такі методи:

   * public Book(String title, String author, String isbn, int publicationYear): Конструктор для створення об'єкта книги з вказаними параметрами.
   * public String getTitle(): Метод для отримання заголовку книги.
   * public String getIsbn(): Метод для отримання ISBN книги.
   * @Override public String toString(): Перевизначений метод для представлення об'єкта книги у вигляді рядка.
  
2. Клас Library: Цей клас представляє бібліотеку та містить список книг, які зберігаються в бібліотеці. Він має такі методи:

   * public Library(): Конструктор для створення об'єкта бібліотеки з пустим списком книг.
   * public void addBook(Book book): Метод для додавання нової книги до бібліотеки.
   * public void displayBooks(): Метод для відображення всіх книг, що зберігаються в бібліотеці.
   * public Book findBookByTitle(String title): Метод для пошуку книги за назвою.
   * public boolean removeBookByIsbn(String isbn): Метод для видалення книги за її ISBN.

3. Клас LibraryManagementSystem: Це головний клас програми, який містить головний метод main. Він відповідає за взаємодію з користувачем через консольний інтерфейс та виклик методів бібліотеки для виконання операцій. Методи:

   * private static int getMenuChoice(Scanner scanner): Метод для вибору опцій у головному меню.
   * private static void addBookToLibrary(Scanner scanner, Library library): Метод для додавання нової книги до бібліотеки.
   * private static void findBookByTitle(Scanner scanner, Library library): Метод для пошуку книги за назвою.
   * private static void removeBookByIsbn(Scanner scanner, Library library): Метод для видалення книги за ISBN.
  
## Зрозуміння цілей програми "Library Management System" за принципом "Five Why's"
1. Чому ця програма була створена?
   * Відповідь: Програма була створена для управління бібліотекою та спрощення процесу додавання, видалення та пошуку книг у бібліотеці.
     
2. Чому потрібно управління бібліотекою?
   * Відповідь: Управління бібліотекою допомагає бібліотекарям та користувачам легше знаходити та контролювати наявність книг.

3. Чому потрібно додавати книги до бібліотеки?
   * Відповідь: Додавання книг дозволяє бібліотеці поповнювати свій асортимент та надавати користувачам доступ до нової літератури.

4. Чому потрібно знаходити книги за назвою та ISBN?
   * Відповідь: Пошук за назвою та ISBN допомагає користувачам швидко знаходити конкретні книги в бібліотеці.

5. Чому потрібно видаляти книги з бібліотеки?
   * Відповідь: Видалення книг дозволяє бібліотеці підтримувати актуальний список книг та звільняти місце для нових.

## Сам код:
``` Java
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

```
