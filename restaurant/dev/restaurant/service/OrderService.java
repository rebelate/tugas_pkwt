package dev.restaurant.service;

import dev.restaurant.model.MenuItem;
import dev.restaurant.model.Order;
import dev.restaurant.repository.MenuRepository;
import dev.restaurant.repository.OrderRepository;

import java.util.List;

public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    public OrderService(OrderRepository orderRepository, MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
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

    @Override
    public void removeItem(int menuItemId) {
        MenuItem menuItem = menuRepository.getById(menuItemId);
        Order order = orderRepository.getCurrentOrder();
        order = order.removeItem(menuItem);
        orderRepository.updateCurrentOrder(order);
    }

    public void removeMultipleItem(int menuItemId) {
        MenuItem menuItem = menuRepository.getById(menuItemId);
        Order order = orderRepository.getCurrentOrder();

        for (int i = 0; i < orderedItemQuantity(menuItemId); i++) {
            order = order.removeItem(menuItem);
        }
        orderRepository.updateCurrentOrder(order);
    }

    public int orderedItemQuantity(int menuItemId) {
        return (int) orderRepository.getCurrentOrder().items().stream().filter(item -> item.id() == menuItemId).count();

    }

    @Override
    public void checkout() {
        Order order = orderRepository.getCurrentOrder();
        orderRepository.clearCurrentOrder();
    }

    @Override
    public Order getCurrentOrder() {
        return orderRepository.getCurrentOrder();
    }

    public List<MenuItem> getCurrentOrderDistinctList() {
        return orderRepository.getCurrentOrder().items().stream().distinct().toList();
    }

    @Override
    public void clearCurrentOrder() {
        orderRepository.clearCurrentOrder();
    }
}