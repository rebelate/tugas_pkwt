package dev.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class BookLoanKey implements Serializable {
    @Serial
    private static final long serialVersionUID = 7214005097114000863L;
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "book_id")
    private Long bookId;

}
