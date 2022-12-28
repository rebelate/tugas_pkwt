package dev.restaurant.view;

import dev.restaurant.model.MenuItem;
import dev.restaurant.model.Order;
import dev.restaurant.service.MenuService;
import dev.restaurant.service.OrderService;
import dev.restaurant.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CashierView {
    private static final Scanner scanner = new Scanner(System.in);
    private MenuService menuService;
    private OrderService orderService;

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

    public void displayOrder(Order order) {
    }

    private void showOrderMenu() {
        String order = handleInput("Enter your orders");
        if (order.isEmpty()) {
            System.out.println("Cancelled");
            return;
        }
        String[] parsed = order.split(",");
        List<MenuItem> orders = new ArrayList<>();
        for (String item : parsed
        ) {
            menuService.getAll().stream().filter(menuItem -> {
                boolean bool = false;
                if (Utils.isContain(menuItem.name().toLowerCase(), item.strip().toLowerCase())) {
                    bool = !menuItem.type().equals("paket") || item.strip().toLowerCase().startsWith("paket");
                }
                return bool;
            }).findFirst().ifPresent(orders::add);
        }
        for (MenuItem item : orders
        ) {
            System.out.println(item);
        }

    }

    private void welcomeMenu() {
        header();
        line();
        System.out.printf("%-47s%s%48s%n", "=", "Commands", "=");
        line();
        String[] menus = {"menu", "order", "change", "delete", "exit"
        };
        for (String item : menus) {
            System.out.printf("%-2s%-100s%s%n", "=", item, "=");
        }
        line();
    }

    private void showMenu() {
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

    public void mainMenu(MenuService menuService, OrderService orderService) {
        this.menuService = menuService;
        this.orderService = orderService;
        try {
            blockMenu:
            while (true) {
                clearScr();
                welcomeMenu();
                String command = handleInput("Enter command");
                switch (command) {
                    case "1":
                    case "menu":
                        showMenu();
                        handleInput();
                        break;
                    case "2":
                    case "order":
                        showOrderMenu();
                        handleInput();
                        break;
                    case "3":
                    case "pay":
                        Thread.sleep(1000);
                        break;
                    case "4":
                    case "exit":
                        break blockMenu;
                    default:
                        System.out.println("Invalid command.");
                        Thread.sleep(500);
                        break;
                }
            }

        } catch (InterruptedException ignored) {
        }
    }
}
