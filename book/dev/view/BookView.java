package dev.view;

import dev.controller.BookController;
import dev.model.Book;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public record BookView(BookController bookController) {
    private static final Scanner scanner = new Scanner(System.in);

    private static void input() {
        scanner.nextLine();
    }

    private static String input(String out) {
        System.out.print(out + ": ");
        return scanner.nextLine();
    }

    private static String inputNotEmpty(String out) {
        System.out.print(out + ": ");
        String input = scanner.nextLine();
        while (input.isEmpty()) {
            System.out.println("Cannot be empty");
            System.out.print(out + ": ");
            input = scanner.nextLine();
        }
        return input;
    }

    private static void clearScr() throws IOException, InterruptedException {
        if (System.getProperty("os.name").equals("Linux")) {
            System.out.print("\033\143");
        }
//            else new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    private static void header() {
        line();
        System.out.printf("%-20s%s%19s%n", "=", "Book Management App", "=");
    }

    private static void line() {
        System.out.println("==========================================================");
    }

    private static void welcomeMenu() {
        header();
        line();
        System.out.printf("%-25s%s%25s%n", "=", "Commands", "=");
        line();
        String[] menus = {"list", "create", "update", "delete", "exit"
        };
        for (String item : menus) {
            System.out.printf("%-2s%-55s%s%n", "=", item, "=");
        }
        line();
    }

    public void displayBooks() throws IOException, InterruptedException {
        List<Book> books = bookController.listBooks();
        clearScr();
        header();
        line();
        System.out.printf("%-24s%s%25s%n", "=", "BOOK LIST", "=");
        line();

        for (Book book : books) {
            String item = "(" + book.getId() + ") " + "'" + book.getTitle() + "'" + " by " + book.getAuthor();
            System.out.printf("%-2s%-55s%s%n", "=", item, "=");
        }
        line();
    }

    public void createBook() {
        String title = inputNotEmpty("Enter book title");
        String author = inputNotEmpty("Enter book author");
        bookController.createBook(title, author);
        System.out.println("Saved!");
    }

    public void updateBook() {
        String title = input("Enter book title");
        Book book = bookController.findBookByTitle(title);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        String newTitle = input("Enter new book title");
        String author = input("Enter new book author");
        book.setTitle(newTitle);
        book.setAuthor(author);
        bookController.updateBook(book);
        System.out.println("Updated!");
    }

    public void deleteBook() {
        String title = input("Enter book title");
        Book book = bookController.findBookByTitle(title);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        bookController.deleteBook(book);
        System.out.println(title + " has been deleted!");
    }

    public void main() throws IOException, InterruptedException {
        blockMenu:
        while (true) {
            clearScr();
            welcomeMenu();
            String command = input("Enter command");
            switch (command) {
                case "list":
                    displayBooks();
                    input();
                    break;
                case "create":
                    createBook();
                    Thread.sleep(1000);
                    break;
                case "update":
                    updateBook();
                    Thread.sleep(1000);
                    break;
                case "delete":
                    deleteBook();
                    Thread.sleep(1000);
                    break;
                case "exit":
                    break blockMenu;
                default:
                    System.out.println("Invalid command.");
                    Thread.sleep(1500);
                    break;
            }
        }
    }
}