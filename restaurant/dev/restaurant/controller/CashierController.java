package dev.restaurant.controller;

import dev.restaurant.model.MenuItem;
import dev.restaurant.model.Order;
import dev.restaurant.service.MenuService;
import dev.restaurant.service.OrderService;
import dev.restaurant.view.CashierView;
import dev.restaurant.view.ReceiptView;

import java.util.List;

public class CashierController {
    private OrderService orderService;
    private MenuService menuService;
    private CashierView cashierView;
    private ReceiptView receiptView;

    public CashierController(OrderService orderService, MenuService menuService, CashierView cashierView, ReceiptView receiptView) {
        this.orderService = orderService;
        this.menuService = menuService;
        this.cashierView = cashierView;
        this.receiptView = receiptView;
    }

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
        List<MenuItem> menus = menuService.getAll();
        cashierView.displayMenu(menus);
    }
}
