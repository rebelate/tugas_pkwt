package dev.restaurant.service;

import dev.restaurant.model.Order;

public interface IOrderService {
    void addItem(int menuItemId);

    void removeItem(int menuItemId);

    void checkout();

    Order getCurrentOrder();
}
