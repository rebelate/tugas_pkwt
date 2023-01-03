package dev.server.dto;

import java.time.LocalDate;

public class BookLoanDto {
    private Long userId;
    private Long bookId;
    private Integer remainingDay;
    private LocalDate dueDate;
    private LocalDate createdDate;
    private boolean isReturned;

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
