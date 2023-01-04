package dev.server.repository;

import dev.server.entity.Book;
import dev.server.entity.BookLoan;
import dev.server.entity.BookLoanKey;
import dev.server.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    @Transactional
    void deleteByBook(Book book);

    boolean existsByBook(Book book);

    List<BookLoan> findAllByUser(User user);

    Optional<BookLoan> findByUserIdAndBookId(Long userId, Long bookId);
}
