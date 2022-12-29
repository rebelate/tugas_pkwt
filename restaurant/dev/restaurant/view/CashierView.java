package dev.restaurant.view;

import dev.restaurant.controller.CashierController;
import dev.restaurant.model.MenuItem;
import dev.restaurant.model.Order;
import dev.restaurant.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CashierView {
    private static final Scanner scanner = new Scanner(System.in);
    public static CashierController controller;

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

    public static void clearScr() {
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
        System.out.printf("%-45s%s%45s%n", "=", "Current Order", "=");
        line();
        List<MenuItem> uniques = controller.getCurrentOrderDistinctList();
        for (int i = 0; i < uniques.size(); i++) {
            MenuItem item = uniques.get(i);
            int count = controller.orderedItemQuantity(item.id());
            System.out.printf("%-2s%-3s%-97s%s%n", "=", i + 1, item.name(), "=");
            System.out.printf("%-5s%s%-96s%s%n", "=", count, " x " + item.price() + ": " + item.price() * count, "=");
        }
    }

    public static void showTotal(int cost) {
        System.out.printf("%-42s%s%43s%n", "=", "Total Cost: " +
                cost, "=");
    }

    public static void showTotalWithTax(int cost) {
        System.out.printf("%-35s%s%s%35s%n", "=", "Total Cost after tax (11%): ",
                cost, "=");
    }

    public static void showReceipt() {
        Order order = controller.getCurrentOrder();
        if (controller.isCurrentOrderEmpty()) {
            System.out.println("You haven't ordered anything");
            handleInput();
            return;
        }
        double cost = order.totalCost();
        double tax = 0.11f;
        double taxAmount = cost * tax;
        int totalWithTax = (int) Math.round(cost + taxAmount);
        displayOrder();
        line();
        showTotalWithTax(totalWithTax);
        line();
        String input = handleInput("Enter Cash");
        if (input.isBlank()) {
            System.out.println("Cancelled");
            handleInput();
            return;
        }
        double cash = Double.parseDouble(input);
        if (cash < totalWithTax) {
            System.out.println("Cash is not sufficient");
            handleInput();
        } else {
            System.out.println("Here's your receipt");
            System.out.println("Total with tax (11%): " + totalWithTax);
            System.out.println("Tax (11%): " + Math.round(taxAmount));
            System.out.println("Total without tax: " + Math.round(cost));
            System.out.println("Your cash: " + Math.round(cash));
            System.out.println("Your change: " + Math.round(cash - totalWithTax));
            System.out.println("Thanks for the visit");
            controller.handleClearOrder();
            handleInput();
        }
    }

    public static void showOrderMenu() {
        showMenu();
        if (!controller.isCurrentOrderEmpty()) {
            displayOrder();
            line();
            showTotal((int) Math.round(controller.getCurrentOrderCost()));
        }
        line();
        String order = handleInput("Enter your orders (eg: geprek, lele, kopi)");
        if (order.isBlank()) {
            if (controller.isCurrentOrderEmpty())
                System.out.println("Cancelled");
            return;
        }
        String[] parsed = order.split(",");
        List<MenuItem> tempOrder = new ArrayList<>();
        for (String item : parsed
        ) {
            controller.getMenuStream().filter(menuItem -> {
                boolean bool = false;
                if (Utils.isContain(menuItem.name().toLowerCase(), item.strip().toLowerCase())) {
                    bool = true;
                    if (menuItem.type().equals("paket") && !item.strip().toLowerCase().startsWith("paket") || tempOrder.contains(menuItem))
                        bool = false;
                }
                return bool;
            }).findFirst().ifPresent(tempOrder::add);
        }
        if (tempOrder.isEmpty()) {
            System.out.println("Nothing found.");
        }
        for (MenuItem item : tempOrder
        ) {
            String input = handleInput("Enter quantity for " + item.name() + " (default is 1)");
            int quantity;
            if (input.isBlank()) quantity = 1;
            else quantity = Integer.parseInt(input);
            controller.addMultipleItem(item, quantity);
            System.out.println(quantity);
        }
        clearScr();
        showOrderMenu();
    }

    public static void showChangeMenu() {
        if (controller.isCurrentOrderEmpty()) {
            System.out.println("You haven't ordered anything");
            handleInput();
            return;
        } else {
            displayOrder();
            line();
        }
        String order = handleInput("Order id that you want to change (eg: 2)");
        if (order.isBlank()) {
            System.out.println("Cancelled");
            handleInput();
            return;
        }
        try {
            MenuItem item = controller.getDistinctOrderById(Integer.parseInt(order) - 1);
            String input = handleInput("Enter new quantity for " + item.name());
            if (input.isBlank()) {
                System.out.println("Cancelled");
                handleInput();
                return;
            }
            controller.removeMultipleItem(item.id());
            int quantity = Integer.parseInt(input);
            if (quantity == 0) {
                System.out.println("Deleted order for " + item.name());
                handleInput();
                return;
            }
            controller.addMultipleItem(item, quantity);
            System.out.println("Updated order for " + item.name());
            handleInput();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No matching order");
            handleInput();
        }
    }

    public static void welcomeMenu() {
        header();
        line();
        System.out.printf("%-47s%s%48s%n", "=", "Commands", "=");
        line();
        String[] menus = {"order", "change", "pay", "clear", "exit"
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
        for (MenuItem item : controller.getMakanan()) {
            System.out.printf("%-2s%-3s%-91s%-6s%s%n", "=", item.id(), item.name(), item.price(), "=");
        }
        line();

        System.out.printf("%-45s%s%46s%n", "=", "Menu Minuman", "=");
        line();
        for (MenuItem item : controller.getMinuman()) {
            System.out.printf("%-2s%-3s%-92s%s%2s%n", "=", item.id(), item.name(), item.price(), "=");
        }
        line();

        System.out.printf("%-46s%s%47s%n", "=", "Menu Paket", "=");
        line();
        for (MenuItem item : controller.getPaket()) {
            System.out.printf("%-2s%-3s%-91s%s%2s%n", "=", item.id(), item.name(), item.price(), "=");
        }
    }

    public static void showClearMenu() {
        if (controller.isCurrentOrderEmpty()) {
            System.out.println("You haven't ordered anything");
            handleInput();
            return;
        }
        if (handleInput("Are you sure?(y|n)", "y", "n").equals("y")) {
            controller.handleClearOrder();
            System.out.println("All Order has been cleared");
        } else System.out.println("Cancelled");
        handleInput();
    }
}
