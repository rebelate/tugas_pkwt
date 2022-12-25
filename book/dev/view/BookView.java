package dev.view;

import dev.controller.BookController;
import dev.model.Book;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public record BookView(BookController bookController) {
    private static final Scanner scanner = new Scanner(System.in);

    private static String input() {
        return scanner.nextLine();
    }

    private static void clearScr() throws IOException, InterruptedException {
        if (System.getProperty("os.name").equals("Linux")) {
            System.out.print("\033\143");
        }
//            else new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public void displayBooks() {
        List<Book> books = bookController.listBooks();
        for (Book book : books) {
            System.out.println(book.getId() + ": " + book.getTitle() + " by " + book.getAuthor());
        }
    }

    public void createBook() {
        System.out.print("Enter book title: ");
        String title = input();
        System.out.print("Enter book author: ");
        String author = input();
        bookController.createBook(title, author);
    }

    public void updateBook() {
        System.out.print("Enter book ID: ");
        String id = input();
        Book book = bookController.findBookById(id);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        System.out.print("Enter new book title: ");
        String title = input();
        System.out.print("Enter new book author: ");
        String author = input();
        book.setTitle(title);
        book.setAuthor(author);
        bookController.updateBook(book);
    }

    public void deleteBook() {
        System.out.print("Enter book ID: ");
        String id = input();
        Book book = bookController.findBookById(id);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        bookController.deleteBook(book);
    }

    public void main() {
        blockMenu:
        while (true) {
            System.out.println("Enter command (list, create, update, delete, exit): ");
            String command = input();
            switch (command) {
                case "list":
                    displayBooks();
                    break;
                case "create":
                    createBook();
                    break;
                case "update":
                    updateBook();
                    break;
                case "delete":
                    deleteBook();
                    break;
                case "exit":
                    break blockMenu;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }
}