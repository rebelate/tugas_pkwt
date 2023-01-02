package dev.server.repository;

import dev.server.entity.BookLoan;
import dev.server.entity.BookLoanKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, BookLoanKey> {
}
