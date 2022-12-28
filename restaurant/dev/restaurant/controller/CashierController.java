package dev.restaurant.controller;

import dev.restaurant.model.MenuItem;
import dev.restaurant.model.Order;
import dev.restaurant.service.MenuService;
import dev.restaurant.service.OrderService;
import dev.restaurant.view.CashierView;
import dev.restaurant.view.ReceiptView;

public record CashierController(
        OrderService orderService,
        MenuService menuService,
        CashierView cashierView,
        ReceiptView receiptView) {

    public void handleAddItemRequest(int menuItemId) {
        MenuItem menuItem = menuService.getById(menuItemId);
        Order order = orderService.getCurrentOrder();
        order = order.addItem(menuItem);
        orderService.updateCurrentOrder(order);
        cashierView.displayOrder(order);
    }

    public void handleRemoveItemRequest(int menuItemId) {
        MenuItem menuItem = menuService.getById(menuItemId);
        Order order = orderService.getCurrentOrder();
        order = order.removeItem(menuItem);
        orderService.updateCurrentOrder(order);
        cashierView.displayOrder(order);
    }

    public void handleCheckoutRequest() {
        Order order = orderService.getCurrentOrder();
        receiptView.displayReceipt(order);
        orderService.clearCurrentOrder();
    }

    public void handleViewOrderRequest() {
        Order order = orderService.getCurrentOrder();
        cashierView.displayOrder(order);
    }

    public void handleViewMenuRequest() {
        cashierView.displayMenu(menuService);
    }
}
