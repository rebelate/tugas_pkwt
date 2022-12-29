package dev.restaurant.repository;

import dev.restaurant.model.Order;

import java.util.ArrayList;

public class OrderRepository implements IOrderRepository {
    private Order currentOrder = new Order(new ArrayList<>(), 0);

    @Override
    public Order getCurrentOrder() {
        return currentOrder;
    }

    @Override
    public void updateCurrentOrder(Order order) {
        currentOrder = order;
    }

    @Override
    public void clearCurrentOrder() {
        currentOrder = new Order(new ArrayList<>(), 0);
    }
}
