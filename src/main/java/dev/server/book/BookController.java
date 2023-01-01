package dev.server.book;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{bookId}")
    Book getBookById(@PathVariable("bookId") Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{bookId}")
    Book updateBookById(@PathVariable("bookId") Long id, @RequestBody BookDto bookDto) {
        System.out.println(bookDto);
        return bookService.updateBookById(id, bookDto);
    }

}
