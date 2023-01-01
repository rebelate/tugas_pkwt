package dev.server.book;

import java.util.List;

public interface IBookService {
    List<Book> getBooks();

    Book getBookById(Long bookId);

    Book updateBookById(Long bookId, BookDto bookDto);
}
