package dev.repository;

import dev.model.Book;

import java.util.List;

public interface BookDAO {
    List<Book> findAll();

    Book findByTitle(String title);

    List<Book> findByAuthor(String author);

    void create(Book book);

    void update(Book book);

    void delete(Book book);
}