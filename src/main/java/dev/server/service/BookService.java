package dev.server.service;

import dev.server.dto.Response;
import dev.server.repository.CategoryRepository;
import dev.server.dto.BookDto;
import dev.server.entity.Book;
import dev.server.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@Service
public class BookService implements IBookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    Logger logger = LoggerFactory.getLogger("Book Service");
    private static final String BOOK_NOT_EXIST = "Book does not exist";
    private static final String CATEGORY_NOT_EXIST = "Category does not exist";
    private static final String CREATE_FAILED = "Failed creating new book";


    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Response getBookList() {
        return Response.generate(bookRepository.findAll());
    }

    @Override
    public Response getBookById(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
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
        Example<Book> bookExample = Example.of(book, matcher);

        List<Book> bookList = bookRepository.findAll(bookExample);
        return Response.generate(bookList);
    }

    @Override
    public Response createBook(BookDto bookDto) {
        List<String> errors = new ArrayList<>();
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
        if (errors.isEmpty()) {
            var savedBook = bookRepository.save(book);
            logger.info("CREATED NEW BOOK WITH ID " + savedBook.getId());
            return Response.generate("book created successfully", savedBook);
        } else {
            String parsedErrors
                    = "Need to specify " + errors.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")
                    .replace(",", ", ");
            return Response.generate(BAD_REQUEST, CREATE_FAILED + ": " + parsedErrors);
        }
    }

    @Override
    public Response updateBookById(Long bookId, BookDto bookDto) {
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
        if (bookDto.category() > 0
        ) {
            var category = categoryRepository.findById(bookDto.category());
            if (category.isEmpty()) return Response.generate(BAD_REQUEST, CATEGORY_NOT_EXIST);
            book.setCategory(category.get());
        }
        logger.info("UPDATED BOOK WITH ID " + book.getId());
        return Response.generate(bookRepository.save(book));
    }

    @Override
    public Response deleteBookById(Long bookId) {
        var bookExist = bookRepository.existsById(bookId);
        if (bookExist)
            bookRepository.deleteById(bookId);
        else
            return Response.generate(BAD_REQUEST, BOOK_NOT_EXIST);
        logger.info("DELETED BOOK WITH ID " + bookId);
        return Response.generate(String.format("id %s deleted", bookId));
    }

}
