package dev.server.book;

import dev.server.Response;

public interface IBookService {
    Response getBooks();

    Response getBookById(Long bookId);

    Response createBook(BookDto bookDto);

    Response updateBookById(Long bookId, BookDto bookDto);

    Response deleteBookById(Long bookId);
}
