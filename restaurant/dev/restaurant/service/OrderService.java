package dev.restaurant.service;

import dev.restaurant.model.MenuItem;
import dev.restaurant.model.Order;
import dev.restaurant.repository.MenuRepository;
import dev.restaurant.repository.OrderRepository;

import java.util.List;

public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void addItem(MenuItem menuItem) {
        Order order = orderRepository.getCurrentOrder();
        order = order.addItem(menuItem);
        orderRepository.updateCurrentOrder(order);
    }

    public void addMultipleItem(MenuItem item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addItem(item);
        }
    }

    public void removeMultipleItem(MenuItem item) {
        Order order = orderRepository.getCurrentOrder();

        for (int i = 0; i < orderedItemQuantity(item.id()); i++) {
            order = order.removeItem(item);
        }
        orderRepository.updateCurrentOrder(order);
    }

    public int orderedItemQuantity(int menuItemId) {
        return (int) orderRepository.getCurrentOrder().items().stream().filter(item -> item.id() == menuItemId).count();

    }

    @Override
    public void checkout() {
        orderRepository.clearCurrentOrder();
    }

    @Override
    public Order getCurrentOrder() {
        return orderRepository.getCurrentOrder();
    }

    public List<MenuItem> getCurrentOrderDistinctList() {
        return orderRepository.getCurrentOrder().items().stream().distinct().toList();
    }
}