import model.Book;
import model.Member;
import service.BookService;
import service.MemberService;
import service.LibraryService;

public class TestRunner {
    public static void main(String[] args) throws Exception {
        BookService books = new BookService();
        MemberService members = new MemberService();
        LibraryService library = new LibraryService(books, members);

        books.addBook(new Book(1, "Test Book", "Test Author", true));
        members.addMember(new Member(10, "Test Member"));

        if (books.findBook(1) == null) throw new AssertionError("Book lookup failed.");

        library.issueBook(1, 10);
        if (books.findBook(1).isAvailable()) throw new AssertionError("Issue failed.");

        library.returnBook(1);
        if (!books.findBook(1).isAvailable()) throw new AssertionError("Return failed.");

        System.out.println("All tests passed.");
    }
}
