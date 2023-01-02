package dev.server.dto;

public record BookDto(String title, String author, String publisher, String description, String category) {
    public Boolean isEmpty() {
        return title == null && author == null && publisher == null && description == null && category == null;
    }
}
