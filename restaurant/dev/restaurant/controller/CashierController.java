package dev.restaurant.controller;

import dev.restaurant.model.MenuItem;
import dev.restaurant.model.Order;
import dev.restaurant.service.MenuService;
import dev.restaurant.service.OrderService;
import dev.restaurant.view.CashierView;
import dev.restaurant.view.ReceiptView;

import java.util.List;
import java.util.stream.Stream;

public record CashierController(
        OrderService orderService,
        MenuService menuService) {

    public List<MenuItem> handleGetMakanan() {
        return menuService.getMakanan();
    }

    public void handleAddItemRequest(int menuItemId) {
        MenuItem menuItem = menuService.getById(menuItemId);
        Order order = orderService.getCurrentOrder();
        order = order.addItem(menuItem);
        orderService.updateCurrentOrder(order);
        CashierView.displayOrder();
    }

    public void handleRemoveItemRequest(int menuItemId) {
        MenuItem menuItem = menuService.getById(menuItemId);
        Order order = orderService.getCurrentOrder();
        order = order.removeItem(menuItem);
        orderService.updateCurrentOrder(order);
        CashierView.displayOrder();
    }

    public void handleCheckoutRequest() {
        Order order = orderService.getCurrentOrder();
        ReceiptView.displayReceipt(order);
        orderService.clearCurrentOrder();
    }

    public void handleStartCashierApp() {
        CashierView.controller = this;
        try {
            blockMenu:
            while (true) {
                CashierView.clearScr();
                CashierView.welcomeMenu();
                String command = CashierView.handleInput("Enter command");
                switch (command) {
                    case "1":
                    case "order":
                        CashierView.showOrderMenu();
                        break;
                    case "2":
                    case "change":
                        CashierView.showChangeMenu();
                        break;
                    case "3":
                    case "remove":
                        Thread.sleep(1000);
                        break;
                    case "4":
                    case "pay":
                        CashierView.handleInput();
                        break;
                    case "5":
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

    public List<MenuItem> getCurrentOrderDistinctList() {
        return orderService.getCurrentOrderDistinctList();
    }

    public int orderedItemQuantity(int id) {
        return orderService.orderedItemQuantity(id);
    }

    public double getCurrentOrderCost() {
        return orderService.getCurrentOrder().totalCost();
    }

    public boolean isCurrentOrderEmpty() {
        return orderService.getCurrentOrder().items().isEmpty();
    }

    public Stream<MenuItem> getMenuStream() {
        return menuService.getAll().stream();
    }

    public void addMultipleItem(MenuItem item, int quantity) {
        orderService.addMultipleItem(item, quantity);
    }

    public MenuItem getDistinctOrderById(int id) {
        return orderService.getCurrentOrderDistinctList().get(id);
    }

    public void removeMultipleItem(int id) {
        orderService.removeMultipleItem(id);
    }

    public MenuItem[] getMakanan() {
        return menuService.getMakanan().toArray(MenuItem[]::new);
    }

    public MenuItem[] getMinuman() {
        return menuService.getMinuman().toArray(MenuItem[]::new);
    }

    public MenuItem[] getPaket() {
        return menuService.getPaket().toArray(MenuItem[]::new);
    }
}
