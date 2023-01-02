package dev.server.controller;

import dev.server.dto.BookDto;
import dev.server.dto.Response;
import dev.server.service.BookService;
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
        return bookService.getBookList();
    }

    @PostMapping
    Response Post(@RequestBody BookDto bookDto) {
        return bookService.createBook(bookDto);
    }

    @GetMapping("/{bookId}")
    Response Get(@PathVariable("bookId") Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping("/search")
    Response Get(@RequestParam(name = "title", required = false) String title,
                 @RequestParam(name = "author", required = false) String author,
                 @RequestParam(name = "publisher", required = false) String publisher) {
        return bookService.findBook(title, author, publisher);
    }

    @PutMapping("/{bookId}")
    Response Put(@PathVariable("bookId") Long id, @RequestBody BookDto bookDto) {
        return bookService.updateBookById(id, bookDto);
    }

    @DeleteMapping("/{bookId}")
    Response Delete(@PathVariable("bookId") Long id) {
        return bookService.deleteBookById(id);
    }

}
