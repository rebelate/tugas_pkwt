package dev.restaurant.controller;

import dev.restaurant.model.MenuItem;
import dev.restaurant.model.Order;
import dev.restaurant.service.MenuService;
import dev.restaurant.service.OrderService;
import dev.restaurant.view.CashierView;
import dev.restaurant.view.ReceiptView;

public record CashierController(
        OrderService orderService,
        MenuService menuService) {

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
        CashierView.menuService = menuService;
        CashierView.orderService = orderService;
        try {
            blockMenu:
            while (true) {
                CashierView.welcomeMenu();
                String command = CashierView.handleInput("Enter command");
                switch (command) {
                    case "1":
                    case "order":
                        CashierView.showMenu();
                        CashierView.showOrderMenu();
                        CashierView.handleInput();
                        break;
                    case "2":
                    case "change":
                        CashierView.showChangeMenu();
                        CashierView.handleInput();
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
}
