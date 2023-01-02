package dev.server.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.server.user.User;
import jakarta.persistence.*;

import java.util.HashSet;
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

    @JsonIgnore
    @ManyToMany(mappedBy = "borrowedBooks")
    private Set<User> users = new HashSet<>();

    protected Book() {
    }

    public Book(String title, String author, String publisher, String description) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
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

    public Set<User> getUsers() {
        return users;
    }

    public Book setUsers(Set<User> users) {
        this.users = users;
        return this;
    }
}
