package dev.restaurant.repository;

import dev.restaurant.model.Order;

public class OrderRepository implements IOrderRepository {
    private Order currentOrder;

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
        currentOrder = null;
    }
}
