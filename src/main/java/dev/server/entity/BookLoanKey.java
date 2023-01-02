package dev.server.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BookLoanKey implements Serializable {
    private static final long serialVersionUID = 8631701526738066402L;
    @Column(name = "user_id")
    @JsonProperty
    Long userId;

    @Column(name = "book_id")
    @JsonProperty
    Long bookId;

    public BookLoanKey() {
    }

    public BookLoanKey(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
