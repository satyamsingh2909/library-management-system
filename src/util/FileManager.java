package util;

import model.Book;
import java.io.*;
import java.util.List;

public class FileManager {
    private final String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public void saveBooks(List<Book> books) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                writer.println(book.getId() + "|" + book.getTitle().replace("|", "/")
                        + "|" + book.getAuthor().replace("|", "/")
                        + "|" + book.isAvailable());
            }
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }

    public void loadBooks(List<Book> books) {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split("\\|", -1);
                if (p.length == 4) {
                    books.add(new Book(Integer.parseInt(p[0]), p[1], p[2],
                            Boolean.parseBoolean(p[3])));
                }
            }
        } catch (Exception e) {
            System.out.println("Could not load data: " + e.getMessage());
        }
    }
}
