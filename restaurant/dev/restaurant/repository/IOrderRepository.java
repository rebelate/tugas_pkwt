package dev.restaurant.repository;

import dev.restaurant.model.Order;

public interface IOrderRepository {
    Order getCurrentOrder();

    void updateCurrentOrder(Order order);

    void clearCurrentOrder();
}
