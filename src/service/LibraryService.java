package service;

import model.Book;
import model.Member;
import model.IssueRecord;
import exception.LibraryException;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private final BookService bookService;
    private final MemberService memberService;
    private final List<IssueRecord> issueRecords = new ArrayList<>();

    public LibraryService(BookService bookService, MemberService memberService) {
        this.bookService = bookService;
        this.memberService = memberService;
    }

    public void issueBook(int bookId, int memberId) throws LibraryException {
        Book book = bookService.findBook(bookId);
        Member member = memberService.findMember(memberId);

        if (book == null) throw new LibraryException("Book not found.");
        if (member == null) throw new LibraryException("Member not found.");
        if (!book.isAvailable()) throw new LibraryException("Book is already issued.");

        book.setAvailable(false);
        issueRecords.add(new IssueRecord(bookId, memberId));
        System.out.println("Book issued successfully.");
    }

    public void returnBook(int bookId) throws LibraryException {
        Book book = bookService.findBook(bookId);
        if (book == null) throw new LibraryException("Book not found.");
        if (book.isAvailable()) throw new LibraryException("Book is not currently issued.");

        book.setAvailable(true);
        issueRecords.removeIf(record -> record.getBookId() == bookId);
        System.out.println("Book returned successfully.");
    }

    public void displayIssuedBooks() {
        if (issueRecords.isEmpty()) {
            System.out.println("No books are currently issued.");
            return;
        }
        System.out.println("\nIssued Books");
        System.out.println("------------");
        for (IssueRecord record : issueRecords) System.out.println(record);
    }
}
