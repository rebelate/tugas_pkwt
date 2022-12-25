package dev.controller;

import dev.model.Book;
import dev.repository.BookRepository;

import java.util.List;

import static dev.utils.Utils.generateUuid;


public record BookController(BookRepository bookRepository) {

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    public Book findBookByTitle(String id) {
        return bookRepository.findByTitle(id);
    }

    public void createBook(String title, String author) {

        Book book = new Book(generateUuid(), title, author);
        bookRepository.create(book);
    }

    public void updateBook(Book book) {
        bookRepository.update(book);
    }

    public void deleteBook(Book book) {
        bookRepository.delete(book);
    }
}