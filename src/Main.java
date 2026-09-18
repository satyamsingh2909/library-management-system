import model.Book;
import model.Member;
import service.BookService;
import service.MemberService;
import service.LibraryService;
import exception.LibraryException;
import util.FileManager;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        BookService bookService = new BookService();
        MemberService memberService = new MemberService();
        LibraryService libraryService = new LibraryService(bookService, memberService);
        FileManager fileManager = new FileManager("data/books.txt");

        fileManager.loadBooks(bookService.getBooks());

        try {
            if (bookService.getBooks().isEmpty()) {
                bookService.addBook(new Book(1, "Clean Code", "Robert C. Martin", true));
                bookService.addBook(new Book(2, "Effective Java", "Joshua Bloch", true));
                bookService.addBook(new Book(3, "Java: The Complete Reference", "Herbert Schildt", true));
            }
            memberService.addMember(new Member(101, "Student One"));
            memberService.addMember(new Member(102, "Student Two"));
        } catch (LibraryException e) {
            System.out.println(e.getMessage());
        }

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {
                    case 1 -> bookService.displayBooks();
                    case 2 -> memberService.displayMembers();
                    case 3 -> addBook(bookService);
                    case 4 -> issueBook(libraryService);
                    case 5 -> returnBook(libraryService);
                    case 6 -> libraryService.displayIssuedBooks();
                    case 7 -> {
                        fileManager.saveBooks(bookService.getBooks());
                        running = false;
                        System.out.println("Data saved. Thank you!");
                    }
                    default -> System.out.println("Invalid choice. Please select 1-7.");
                }
            } catch (LibraryException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n================================");
        System.out.println("     LIBRARY MANAGEMENT SYSTEM");
        System.out.println("================================");
        System.out.println("1. View Books");
        System.out.println("2. View Members");
        System.out.println("3. Add Book");
        System.out.println("4. Issue Book");
        System.out.println("5. Return Book");
        System.out.println("6. View Issued Books");
        System.out.println("7. Exit");
    }

    private static void addBook(BookService service) throws LibraryException {
        int id = readInt("Enter book ID: ");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        service.addBook(new Book(id, title, author, true));
        System.out.println("Book added successfully.");
    }

    private static void issueBook(LibraryService service) throws LibraryException {
        int bookId = readInt("Enter book ID: ");
        int memberId = readInt("Enter member ID: ");
        service.issueBook(bookId, memberId);
    }

    private static void returnBook(LibraryService service) throws LibraryException {
        int bookId = readInt("Enter book ID: ");
        service.returnBook(bookId);
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
