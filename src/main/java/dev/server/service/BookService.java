package dev.server.service;

import dev.server.dto.BookDto;
import dev.server.dto.Response;
import dev.server.entity.Book;
import dev.server.repository.BookLoanRepository;
import dev.server.repository.BookRepository;
import dev.server.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@Service
public class BookService implements IBookService {
    private final BookRepository bookRepository;
    private final BookLoanRepository bookLoanRepository;
    private final CategoryRepository categoryRepository;
    Logger logger = LoggerFactory.getLogger("Book Service");
    private static final String BOOK_ALREADY_EXIST = "Book with the same title already exist";
    static final String BOOK_NOT_EXIST = "Book does not exist";
    private static final String CATEGORY_NOT_EXIST = "Category does not exist";
    private static final String BOOK_CREATE_FAIL = "Failed creating new book";
    private static final String BOOK_CREATE_SUCCESS = "Book created successfully";
    private static final String BOOK_UPDATE_SUCCESS = "Book updated successfully";
    private static final String BOOK_UPDATE_EMPTY = "Nothing updated";


    public BookService(BookRepository bookRepository, BookLoanRepository bookLoanRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.bookLoanRepository = bookLoanRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Response getBookList() {
        return Response.generate(bookRepository.findAll());
    }

    @Override
    public Response getBookById(Long bookId) {
        var optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            return Response.generate(BAD_REQUEST, BOOK_NOT_EXIST);
        }
        return Response.generate(optionalBook.get());
    }

    public Response findBook(String title, String author, String publisher) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("title", match -> match.contains().ignoreCase())
                .withMatcher("author", match -> match.startsWith().ignoreCase())
                .withMatcher("publisher", match -> match.startsWith().ignoreCase());
        Book book = new Book();
        book.setTitle(title).setAuthor(author).setPublisher(publisher);
        var bookExample = Example.of(book, matcher);

        var bookList = bookRepository.findAll(bookExample);
        return Response.generate(bookList);
    }

    @Override
    public Response createBook(BookDto bookDto) {
        if (alreadyExist(bookDto.title())) return Response.generate(BAD_REQUEST, BOOK_ALREADY_EXIST);

        var errors = new ArrayList<String>();
        var book = new Book();

        if (bookDto.author() != null
        ) {
            book.setAuthor(bookDto.author());
        } else errors.add("author");
        if (bookDto.title() != null
        ) {
            book.setTitle(bookDto.title());
        } else errors.add("title");
        if (bookDto.publisher() != null
        ) {
            book.setPublisher(bookDto.publisher());
        } else errors.add("publisher");
        if (bookDto.description() != null
        ) {
            book.setDescription(bookDto.description());
        }
        if (bookDto.category() != null
        ) {
            var category = categoryRepository.findByName(bookDto.category());
            if (category.isEmpty()) return Response.generate(BAD_REQUEST, CATEGORY_NOT_EXIST);
            book.setCategory(category.get());
        } else {
            book.setCategory(null);
        }
        if (bookDto.copies() != null
        ) {
            book.setCopies(bookDto.copies());
        }
        if (errors.isEmpty()) {
            var savedBook = bookRepository.save(book);
            logger.info("CREATED NEW BOOK WITH ID " + savedBook.getId());
            return Response.generate(BOOK_CREATE_SUCCESS, savedBook);
        } else {
            String parsedErrors
                    = "Need to specify " + errors.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")
                    .replace(",", ", ");
            return Response.generate(BAD_REQUEST, BOOK_CREATE_FAIL + ": " + parsedErrors);
        }
    }

    @Override
    public Response updateBookById(Long bookId, BookDto bookDto) {
        if (alreadyExist(bookDto.title())) return Response.generate(BAD_REQUEST, BOOK_ALREADY_EXIST);

        var optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isEmpty()) {
            return Response.generate(BAD_REQUEST, BOOK_NOT_EXIST);
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
        if (bookDto.category() != null
        ) {
            var category = categoryRepository.findByName(bookDto.category());
            if (category.isEmpty()) return Response.generate(BAD_REQUEST, CATEGORY_NOT_EXIST);
            book.setCategory(category.get());
        } else {
            book.setCategory(null);
        }
        if (bookDto.copies() != null
        ) {
            book.setCopies(bookDto.copies());
        }
        logger.info("UPDATED BOOK WITH ID " + book.getId());
        return Response.generate(BOOK_UPDATE_SUCCESS, bookRepository.save(book));
    }

    @Override
    public Response deleteBookById(Long bookId) {
        var optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            var book = optionalBook.get();
            if (bookLoanRepository.existsByBook(book)) bookLoanRepository.deleteByBook(book);
            bookRepository.deleteById(bookId);
        } else return Response.generate(BAD_REQUEST, BOOK_NOT_EXIST);
        logger.info("DELETED BOOK WITH ID " + bookId);
        return Response.generate(String.format("id %s deleted", bookId));
    }

    @Override
    public boolean alreadyExist(String name) {
        return bookRepository.findByTitle(name).isPresent();
    }
}
