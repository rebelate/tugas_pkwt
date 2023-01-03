package dev.server.repository;

import dev.server.entity.Book;
import dev.server.entity.BookLoan;
import dev.server.entity.BookLoanKey;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, BookLoanKey> {
    @Transactional
    void deleteByBook(Book book);

    boolean existsByBook(Book book);
}
