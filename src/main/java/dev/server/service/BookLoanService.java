package dev.server.service;

import dev.server.dto.Response;
import dev.server.entity.Book;
import dev.server.entity.BookLoan;
import dev.server.entity.BookLoanKey;
import dev.server.entity.User;
import dev.server.repository.BookLoanRepository;
import dev.server.repository.BookRepository;
import dev.server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;

import static dev.server.service.BookService.BOOK_NOT_EXIST;
import static dev.server.service.UserService.USER_NOT_EXIST;
import static org.springframework.http.HttpStatus.BAD_REQUEST;


@Service
public class BookLoanService {
    private static final String NOT_EXIST = "Book loan does not exist";
    private static final String NO_COPIES = "Book is not available to loan at the moment";
    private static final String BOOK_LOAN_CREATE = "Book loan created successfully";
    private final BookLoanRepository bookLoanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger("Book Loan Service");


    public BookLoanService(BookLoanRepository bookLoanRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.bookLoanRepository = bookLoanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public Response getBookLoanList() {
        return Response.generate(bookLoanRepository.findAll());
    }

    public Response createBookLoan(Integer userId, Integer bookId, Integer duration) {
        var errors = new ArrayList<String>();
        if (userId == null) errors.add("userId");
        if (bookId == null) errors.add("bookId");
        if (duration == null) errors.add("duration");
        if (!errors.isEmpty()) {
            String parsedErrors
                    = "Need to specify " + errors.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")
                    .replace(",", ", ");
            return Response.generate(BAD_REQUEST, parsedErrors);
        }
        Optional<User> optionalUser = userRepository.findById(userId.longValue());
        Optional<Book> optionalBook = bookRepository.findById(bookId.longValue());
        if (optionalUser.isEmpty())
            return Response.generate(BAD_REQUEST, USER_NOT_EXIST);
        if (optionalBook.isEmpty())
            return Response.generate(BAD_REQUEST, BOOK_NOT_EXIST);
        User user = optionalUser.get();
        Book book = optionalBook.get();
        if (book.getCopies() == 0) {
            return Response.generate(BAD_REQUEST, NO_COPIES);
        } else book.loanBook();
        LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Jakarta"));
        LocalDate dueDate = localDate.plusDays(duration);
        BookLoan bookLoan = new BookLoan(user, book, dueDate);
        return Response.generate(BOOK_LOAN_CREATE,bookLoanRepository.save(bookLoan));
    }

    public Response returnBookLoan(Integer userId, Integer bookId) {
        var errors = new ArrayList<String>();
        if (userId == null) errors.add("userId");
        if (bookId == null) errors.add("bookId");
        if (!errors.isEmpty()) {
            String parsedErrors
                    = "Need to specify " + errors.toString()
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")
                    .replace(",", ", ");
            return Response.generate(BAD_REQUEST, parsedErrors);
        }
        BookLoanKey bookLoanKey = new BookLoanKey(userId.longValue(), bookId.longValue());
        Optional<BookLoan> optionalBookLoan = bookLoanRepository.findById(bookLoanKey);
        if (optionalBookLoan.isEmpty())
            return Response.generate(BAD_REQUEST, NOT_EXIST);
        BookLoan bookloan = optionalBookLoan.get();
        bookloan.getBook().returnBook();
        bookloan.setReturned(true);
        bookLoanRepository.save(bookloan);
        return Response.generate(String.format("Book with id %s returned", bookId));
    }
}
