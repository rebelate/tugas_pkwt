package dev.server.book;

import java.io.Serializable;

public record BookDto(String title, String author, String publisher, String description) implements Serializable {
}
