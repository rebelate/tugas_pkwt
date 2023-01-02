package dev.server.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

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
    private User user;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @Transient
    private int remainingDay;

    private LocalDate dueDate;

}