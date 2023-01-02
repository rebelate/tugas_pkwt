package dev.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Entity
public class BookLoan {
    @EmbeddedId
    private BookLoanKey id;
//    @Id
//    @Column(name = "id", nullable = false)
//    private Long id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    @JsonIgnore
    private Book book;

    @Transient
    private int remainingDay;

    private LocalDate dueDate;

    private LocalDate createdAt;

    public BookLoan() {
    }

    public BookLoan(User user, Book book, LocalDate dueDate) {
        this.id = new BookLoanKey(user.getId(), book.getId());
        this.user = user;
        this.book = book;
        this.dueDate = dueDate;
        this.createdAt = LocalDate.now(ZoneId.of("Asia/Jakarta"));
    }

    public BookLoanKey getId() {
        return id;
    }

    public BookLoan setId(BookLoanKey id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public BookLoan setUser(User user) {
        this.user = user;
        return this;
    }

    public Book getBook() {
        return book;
    }

    public BookLoan setBook(Book book) {
        this.book = book;
        return this;
    }

    public int getRemainingDay() {
        final LocalDate today = LocalDate.now(ZoneId.of("Asia/Jakarta"));
        return (int) today.until(dueDate, ChronoUnit.DAYS);
    }

    public BookLoan setRemainingDay(int remainingDay) {
        this.remainingDay = remainingDay;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BookLoan setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public BookLoan setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }
}