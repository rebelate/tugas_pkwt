package dev.restaurant.service;

import dev.restaurant.model.MenuItem;
import dev.restaurant.model.Order;

public interface IOrderService {
    void addItem(MenuItem menuItem);

    void removeItem(int menuItemId);

    void checkout();

    Order getCurrentOrder();
}
