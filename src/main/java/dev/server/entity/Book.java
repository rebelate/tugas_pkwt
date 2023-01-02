package dev.server.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(length = 100)
    private String title;

    private String author;
    private String publisher;

    @Column(columnDefinition = "TEXT")
    private String description;
    private int copies = 1;

    @OneToMany(mappedBy = "book")
    private Set<BookLoan> bookLoan;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_fk", referencedColumnName = "id")
    private Category category;

    public Book() {
    }

    public Book(String title, String author, String publisher, String description) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
    }

    public Book(String title, String author, String publisher, String description, Category category) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.category = category;
    }

    public Book loanBook() {
        copies = copies - 1;
        return this;
    }

    public Book returnBook() {
        copies = copies + 1;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Book setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public Book setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Book setDescription(String description) {
        this.description = description;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Book setCategory(Category category) {
        this.category = category;
        return this;
    }

    public int getCopies() {
        return copies;
    }

    public Book setCopies(int copies) {
        this.copies = copies;
        return this;
    }
}
