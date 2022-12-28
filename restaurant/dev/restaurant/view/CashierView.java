package dev.restaurant.view;

import dev.restaurant.model.MenuItem;
import dev.restaurant.model.Order;
import dev.restaurant.service.MenuService;
import dev.restaurant.service.OrderService;
import dev.restaurant.utils.Utils;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CashierView {
    private static final Scanner scanner = new Scanner(System.in);
    public static MenuService menuService;
    public static OrderService orderService;

    public static void handleInput() {
        scanner.nextLine();
    }

    public static String handleInput(String out) {
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
        while (input.isBlank()) {
            System.out.println("Cannot be empty");
            System.out.print(out + ": ");
            input = scanner.nextLine();
        }
        return input;
    }

    private static void clearScr() {
        if (System.getProperty("os.name").equals("Linux")) {
            System.out.print("\033\143");
        }
//            else new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    private static void header() {
        line();
        System.out.printf("%-42s%s%41s%n", "=", "Restoran Ayam geprek", "=");
    }

    private static void line() {
        System.out.println("=======================================================================================================");
    }

    public static void displayOrder() {
        line();
        System.out.printf("%-42s%s%41s%n", "=", "Current Order", "=");
        for (MenuItem item : orderService.getCurrentOrder().items()
        ) {
            System.out.printf("%-2s%-100s%s%n", "=", item.name(), "=");
        }
    }

    public static void showOrderMenu() {
        String order = handleInput("Enter your orders (eg: geprek, lele, kopi)");
        if (order.isEmpty()) {
            System.out.println("Cancelled");
            return;
        }
        String[] parsed = order.split(",");
        Set<MenuItem> tempOrder = new HashSet<>();
        for (String item : parsed
        ) {
            menuService.getAll().stream().filter(menuItem -> {
                boolean bool = false;
                if (Utils.isContain(menuItem.name().toLowerCase(), item.strip().toLowerCase())) {
                    bool = true;
                    if (menuItem.type().equals("paket") && !item.strip().toLowerCase().startsWith("paket"))
                        bool = false;
                }
                return bool;
            }).findFirst().ifPresent(tempOrder::add);
        }
        for (MenuItem item : tempOrder
        ) {
            String input = handleInput("Enter quantity for " + item.name() + " (default is 1)");
            int quantity;
            if (input.isBlank()) quantity = 1;
            else
                quantity = Integer.parseInt(input);
            for (int i = 0; i < quantity; i++) {
                orderService.addItem(item);
            }
            System.out.println(quantity);
        }
        displayOrder(orderService.getCurrentOrder());
    }

    public static void showChangeMenu() {
    }

    public static void welcomeMenu() {
        header();
        line();
        System.out.printf("%-47s%s%48s%n", "=", "Commands", "=");
        line();
        String[] menus = {"order", "change", "remove", "pay", "exit"
        };
        for (String item : menus) {
            System.out.printf("%-2s%-100s%s%n", "=", item, "=");
        }
        line();
    }

    public static void showMenu() {
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
