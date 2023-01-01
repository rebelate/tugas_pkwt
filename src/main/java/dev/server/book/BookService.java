package dev.server.book;

import dev.server.Response;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@Service
public class BookService implements IBookService {
    private final BookRepository bookRepository;
    private static final String NOT_EXIST = "Book does not exist";
    private static final String CREATE_FAILED = "Failed creating new book";


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Response getBooks() {
        return Response.generate(bookRepository.findAll());
    }

    @Override
    public Response getBookById(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            return Response.generate(BAD_REQUEST, NOT_EXIST);
        }
        return Response.generate(optionalBook.get());
    }

    @Override
    public Response createBook(BookDto bookDto) {
        if (bookDto.author() != null && bookDto.title() != null && bookDto.publisher() != null && bookDto.description() != null
        ) {
            var book = new Book(bookDto.title(), bookDto.author(), bookDto.publisher(), bookDto.description());
            var savedBook = bookRepository.save(book);
            Logger.info("[BOOK]: CREATED NEW BOOK WITH ID " + savedBook.getId());
            return Response.generate(savedBook, "book created successfully");
        } else
            return Response.generate(BAD_REQUEST, CREATE_FAILED);
    }

    @Override
    public Response updateBookById(Long bookId, BookDto bookDto) {
        var optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            return Response.generate(BAD_REQUEST, NOT_EXIST);
        }
        var book = optionalBook.get();
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
        return Response.generate(bookRepository.save(book));
    }

    @Override
    public Response deleteBookById(Long bookId) {
        var bookExist = bookRepository.existsById(bookId);
        if (bookExist)
            bookRepository.deleteById(bookId);
        else
            return Response.generate(BAD_REQUEST, NOT_EXIST);
        Logger.info("DELETED BOOK WITH ID " + bookId);
        return Response.generate(String.format("id %s deleted", bookId));
    }
}
