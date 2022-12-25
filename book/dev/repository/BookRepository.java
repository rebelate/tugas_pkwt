package dev.repository;

import dev.model.Book;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements BookDAO {
    private final List<Book> books = new ArrayList<>();
    private static final String DATA_FILE_NAME = "books.txt";

    public BookRepository() {
        init();
    }

    public void init() {
        File dataFile = new File(DATA_FILE_NAME);
        if (dataFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    String id = parts[0];
                    String title = parts[1];
                    String author = parts[2];
                    Book book = new Book(id, title, author);
                    books.add(book);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Book findById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public void create(Book book) {
        books.add(book);
        writeBooksToFile(books);
    }

    @Override
    public void update(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(book.getId())) {
                books.set(i, book);
                break;
            }
        }
        writeBooksToFile(books);
    }

    @Override
    public void delete(Book book) {
        books.removeIf(b -> b.getId().equals(book.getId()));
        writeBooksToFile(books);
    }

    private void writeBooksToFile(List<Book> books) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_NAME))) {
            for (Book book : books) {
                writer.write(book.getId() + "," + book.getTitle() + "," + book.getAuthor());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
