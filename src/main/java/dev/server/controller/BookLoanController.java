package dev.server.controller;

import dev.server.payload.Response;
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

    @GetMapping("/{loanId}")
    Response Get(@PathVariable("loanId") Long loanId) {
        return bookLoanService.getBookLoanById(loanId);
    }

    @GetMapping("/user/{userId}")
    Response GetByUserId(@PathVariable("userId") Long userId) {
        return bookLoanService.getBookLoanListByUserId(userId);
    }

    @PostMapping
    Response NewBookLoan(@RequestParam(name = "userId", required = false) Long userId,
                         @RequestParam(name = "bookId", required = false) Long bookId,
                         @RequestParam(name = "duration", required = false) Integer day) {
        return bookLoanService.createBookLoan(userId, bookId, day);
    }

    @GetMapping("/{loanId}/return")
    Response ReturnBookLoan(@PathVariable("loanId") Long loanId) {
        return bookLoanService.returnBookLoan(loanId);
    }
}
