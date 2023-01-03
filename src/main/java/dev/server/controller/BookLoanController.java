package dev.server.controller;

import dev.server.dto.Response;
import dev.server.service.BookLoanService;
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

    @GetMapping("/{userId}")
    Response Get(@PathVariable("userId") Long userId) {
        return bookLoanService.getBookLoanListByUserId(userId);
    }

    @GetMapping("/loan")
    Response NewBookLoan(@RequestParam(name = "userId", required = false) Long userId,
                         @RequestParam(name = "bookId", required = false) Long bookId,
                         @RequestParam(name = "duration", required = false) Integer day) {
        return bookLoanService.createBookLoan(userId, bookId, day);
    }

    @GetMapping("/return")
    Response ReturnBookLoan(@RequestParam(name = "userId", required = false) Long userId,
                            @RequestParam(name = "bookId", required = false) Long bookId) {
        return bookLoanService.returnBookLoan(userId, bookId);
    }
}
