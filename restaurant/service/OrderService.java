package service;

import model.MenuItem;
import model.Order;
import repository.MenuRepository;
import repository.OrderRepository;

public class OrderService {
    private OrderRepository orderRepository;
    private MenuRepository menuRepository;

    public OrderService(OrderRepository orderRepository, MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
    }

    public void addItem(int menuItemId) {
        MenuItem menuItem = menuRepository.getById(menuItemId);
        Order order = orderRepository.getCurrentOrder();
        order = order.addItem(menuItem);
        orderRepository.updateCurrentOrder(order);
    }

    public void removeItem(int menuItemId) {
        MenuItem menuItem = menuRepository.getById(menuItemId);
        Order order = orderRepository.getCurrentOrder();
        order = order.removeItem(menuItem);
        orderRepository.updateCurrentOrder(order);
    }

    public void checkout() {
        Order order = orderRepository.getCurrentOrder();
        orderRepository.clearCurrentOrder();
    }

    public Order getCurrentOrder() {
        return orderRepository.getCurrentOrder();
    }
}