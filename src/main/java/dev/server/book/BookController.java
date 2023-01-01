package dev.server.book;

import dev.server.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    Response Get() {
        return bookService.getBooks();
    }

    @PostMapping
    Response Post(@RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @GetMapping("/{bookId}")
    Response Get(@PathVariable("bookId") Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{bookId}")
    Book Put(@PathVariable("bookId") Long id, @RequestBody BookDto bookDto) {
        return bookService.updateBookById(id, bookDto);
    }

    @DeleteMapping("/{bookId}")
    String Delete(@PathVariable("bookId") Long id) {
        return bookService.deleteBookById(id);
    }

}
