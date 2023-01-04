package dev.server.repository;

import dev.server.entity.Book;
import dev.server.entity.BookLoan;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {
    @Transactional
    void deleteByBook(Book book);

    boolean existsByBook(Book book);

    List<BookLoan> findByUserId(Long id);

//    @Query(value = "select * from book_loan where user_id = ?1", nativeQuery = true)
//    List<BookLoan> findByUserId2(Long id);
}
