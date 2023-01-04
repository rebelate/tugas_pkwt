package dev.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Entity
public class BookLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private boolean isReturned;

    @Transient
    private int remainingDay;

    private LocalDate dueDate;

    private LocalDate createdDate;

    public BookLoan() {
    }

    public BookLoan(User user, Book book, LocalDate dueDate) {
        this.user = user;
        this.book = book;
        this.dueDate = dueDate;
        this.createdDate = LocalDate.now(ZoneId.of("Asia/Jakarta"));
    }

    public Long getId() {
        return id;
    }

    public BookLoan setId(Long id) {
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

    public boolean isReturned() {
        return isReturned;
    }

    public BookLoan setReturned(boolean returned) {
        isReturned = returned;
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public BookLoan setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }
}