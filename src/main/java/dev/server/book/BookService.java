package dev.server.book;

import dev.server.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.Optional;


@Service
public class BookService implements IBookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Response getBooks() {
        return Response.generateResponse(bookRepository.findAll());
    }

    @Override
    public Response getBookById(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            return Response.generateResponse(HttpStatus.BAD_REQUEST, "Book does not exist");
        }
        return Response.generateResponse(optionalBook.get());
    }

    @Override
    public Response createBook(BookDto bookDto) {
        if (bookDto.author() != null && bookDto.title() != null && bookDto.publisher() != null && bookDto.description() != null
        ) {
            var book = new Book(bookDto.title(), bookDto.author(), bookDto.publisher(), bookDto.description());
            var savedBook = bookRepository.save(book);
            Logger.info("[BOOK]: CREATED NEW BOOK WITH ID " + savedBook.getId());
            return Response.generateResponse(savedBook, "book created successfully");
        } else
            return Response.generateResponse(HttpStatus.BAD_REQUEST, "failed to create new book");
    }

    @Override
    public Book updateBookById(Long bookId, BookDto bookDto) {
        var book = bookRepository.findById(bookId).orElseThrow(() ->
                new IllegalStateException("Book does not exist"));
        if (bookDto.author() != null
        ) {
            book.setAuthor(bookDto.author());
        }
        if (bookDto.title() != null
        ) {
            book.setTitle(bookDto.title());
        }
        if (bookDto.publisher() != null
        ) {
            book.setPublisher(bookDto.publisher());
        }
        if (bookDto.description() != null
        ) {
            book.setDescription(bookDto.description());
        }
        Logger.info("UPDATED BOOK WITH ID " + book.getId());
        return book;
    }

    @Override
    public String deleteBookById(Long bookId) {
        var bookExist = bookRepository.existsById(bookId);
        if (bookExist)
            bookRepository.deleteById(bookId);
        else throw new IllegalStateException("Book does not exist");
        Logger.info("DELETED BOOK WITH ID " + bookId);
        return String.format("id %s deleted", bookId);
    }
}
