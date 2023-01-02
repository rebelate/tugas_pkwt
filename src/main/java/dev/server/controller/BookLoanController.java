package dev.server.controller;

import dev.server.dto.BookDto;
import dev.server.dto.Response;
import dev.server.service.BookLoanService;
import dev.server.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookloans")
public class BookLoanController {
    private final BookLoanService bookLoanService;

    public BookLoanController(BookLoanService bookLoanService) {
        this.bookLoanService = bookLoanService;
    }

    @GetMapping
    Response Get() {
        return bookLoanService.getBookLoanList();
    }

    @GetMapping("/loan")
    Response NewBookLoan(@RequestParam(name = "userId", required = false) Integer userId,
                         @RequestParam(name = "bookId", required = false) Integer bookId,
                         @RequestParam(name = "duration", required = false) Integer day) {
        return bookLoanService.createBookLoan(userId, bookId, day);
    }
    @GetMapping("/return")
    Response ReturnBookLoan(@RequestParam(name = "userId", required = false) Integer userId,
                         @RequestParam(name = "bookId", required = false) Integer bookId){
        return bookLoanService.returnBookLoan(userId, bookId);
    }
}
