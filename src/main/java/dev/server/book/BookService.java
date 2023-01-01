package dev.server.book;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new IllegalStateException("Book not found"));
    }

    @Override
    @Transactional
    public Book updateBookById(Long bookId, BookDto bookDto) {
        Book requestedBook = bookRepository.findById(bookId).orElseThrow(() ->
                new IllegalStateException("Book not found"));
        if (bookDto.author() != null
        ) {
            requestedBook.setAuthor(bookDto.author());
        }
        if (bookDto.title() != null
        ) {
            requestedBook.setTitle(bookDto.title());
        }
        if (bookDto.publisher() != null
        ) {
            requestedBook.setPublisher(bookDto.publisher());
        }
        if (bookDto.description() != null
        ) {
            requestedBook.setDescription(bookDto.description());
        }
        return requestedBook;
    }
}
