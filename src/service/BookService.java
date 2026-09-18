package service;

import model.Book;
import exception.LibraryException;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) throws LibraryException {
        if (book.getTitle().isBlank() || book.getAuthor().isBlank()) {
            throw new LibraryException("Book title and author cannot be empty.");
        }
        if (findBook(book.getId()) != null) {
            throw new LibraryException("Book ID already exists.");
        }
        books.add(book);
    }

    public Book findBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\nID | Title | Author | Status");
        System.out.println("-----------------------------------------------");
        for (Book book : books) System.out.println(book);
    }
}
