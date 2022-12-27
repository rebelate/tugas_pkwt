package controller;

import model.MenuItem;
import model.Order;
import repository.MenuRepository;
import repository.OrderRepository;
import view.CashierView;
import view.ReceiptView;

import java.util.List;

public class CashierController {
    private OrderRepository orderRepository;
    private MenuRepository menuRepository;
    private CashierView cashierView;
    private ReceiptView receiptView;

    public CashierController(OrderRepository orderRepository, MenuRepository menuRepository, CashierView cashierView, ReceiptView receiptView) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
        this.cashierView = cashierView;
        this.receiptView = receiptView;
    }

    public void handleAddItemRequest(int menuItemId) {
        MenuItem menuItem = menuRepository.getById(menuItemId);
        Order order = orderRepository.getCurrentOrder();
        order = order.addItem(menuItem);
        orderRepository.updateCurrentOrder(order);
//        cashierView.displayOrder(order);
    }

    public void handleRemoveItemRequest(int menuItemId) {
        MenuItem menuItem = menuRepository.getById(menuItemId);
        Order order = orderRepository.getCurrentOrder();
        order = order.removeItem(menuItem);
        orderRepository.updateCurrentOrder(order);
//        cashierView.displayOrder(order);
    }

    public void handleCheckoutRequest() {
        Order order = orderRepository.getCurrentOrder();
//        receiptView.displayReceipt(order);
        orderRepository.clearCurrentOrder();
    }

    public void handleViewOrderRequest() {
        Order order = orderRepository.getCurrentOrder();
//        cashierView.displayOrder(order);
    }

    public void handleViewMenuRequest() {
        List<MenuItem> menu = menuRepository.getAll();
//        cashierView.displayMenu(menu);
    }
}
