package dev.server.dto;

import java.time.LocalDate;

public class BookLoanDto {
    private Long id;
    private Long userId;
    private String username;
    private Long bookId;
    private String bookTitle;
    private Integer remainingDay;
    private LocalDate dueDate;
    private LocalDate createdDate;
    private boolean isReturned;

    public Long getId() {
        return id;
    }

    public BookLoanDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public BookLoanDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public BookLoanDto setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public Integer getRemainingDay() {
        return remainingDay;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public BookLoanDto setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public BookLoanDto setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    public BookLoanDto setRemainingDay(Integer remainingDay) {
        this.remainingDay = remainingDay;
        return this;
    }

    public BookLoanDto setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public BookLoanDto setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public BookLoanDto setReturned(boolean returned) {
        isReturned = returned;
        return this;
    }
}
