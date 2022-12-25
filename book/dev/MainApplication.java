package dev;

import dev.controller.BookController;
import dev.repository.BookRepository;
import dev.view.BookView;

public class MainApplication {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        BookController bookController = new BookController(bookRepository);
        BookView bookView = new BookView(bookController);
        bookView.main();
    }
}
