package dev.server.service;

import dev.server.dto.Response;
import dev.server.dto.BookDto;

public interface IBookService {
    Response getBookList();

    Response getBookById(Long bookId);

    Response createBook(BookDto bookDto);

    Response updateBookById(Long bookId, BookDto bookDto);

    Response deleteBookById(Long bookId);

    boolean alreadyExist(String name);
}
