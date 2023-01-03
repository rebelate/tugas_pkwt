package dev.server.dto;

public record BookDto(String title, String author, String publisher, String description, String category, Integer copies) {
}
