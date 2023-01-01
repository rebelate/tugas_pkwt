package dev.server.book;

import dev.server.Response;

public interface IBookService {
    Response getBooks();

    Response getBookById(Long bookId);

    Response createBook(BookDto bookDto);

    Book updateBookById(Long bookId, BookDto bookDto);

    String deleteBookById(Long bookId);
}
