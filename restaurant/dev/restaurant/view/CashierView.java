package dev.restaurant.view;

import dev.restaurant.model.MenuItem;
import dev.restaurant.model.Order;
import dev.restaurant.service.MenuService;

import java.io.IOException;
import java.util.Scanner;

public class CashierView {
    private static final Scanner scanner = new Scanner(System.in);

    private static void handleInput() {
        scanner.nextLine();
    }

    private static String handleInput(String out) {
        System.out.print(out + ": ");
        return scanner.nextLine();
    }

    /**
     * Strict input for the defined types
     */
    private static String handleInput(String out, String... types) {
        System.out.print(out + ": ");
        String input = scanner.nextLine();
        String parsedType = String.join(" | ", types);
        boolean ok = false;
        while (!ok) {
            for (String type : types
            ) {
                if (input.equals(type)) {
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                System.out.print("Input should either be (" + parsedType + "): ");
                input = scanner.nextLine();
            }
        }
        return input;
    }

    private static String handleInputNotEmpty(String out) {
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
        System.out.printf("%-46s%s%46s%n", "=", "Cashier App", "=");
    }

    private static void line() {
        System.out.println("=======================================================================================================");
    }

    public void displayOrder(Order order) {
    }

    public void displayMenu(MenuService menuService) {
        header();
        line();
        System.out.printf("%-46s%s%45s%n", "=", "Menu Makanan", "=");
        line();
        for (MenuItem item : menuService.getMakanan()) {
            System.out.printf("%-2s%-3s%-91s%-6s%s%n", "=", item.id(), item.name(), item.price(), "=");
        }
        line();

        System.out.printf("%-45s%s%46s%n", "=", "Menu Minuman", "=");
        line();
        for (MenuItem item : menuService.getMinuman()) {
            System.out.printf("%-2s%-3s%-92s%s%2s%n", "=", item.id(), item.name(), item.price(), "=");
        }
        line();

        System.out.printf("%-46s%s%47s%n", "=", "Menu Paket", "=");
        line();
        for (MenuItem item : menuService.getPaket()) {
            System.out.printf("%-2s%-3s%-91s%s%2s%n", "=", item.id(), item.name(), item.price(), "=");
        }
        line();
    }
}
