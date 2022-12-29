package dev.restaurant.service;

import dev.restaurant.model.MenuItem;
import dev.restaurant.model.Order;

public interface IOrderService {
    enum Status{
        SUCCESS,
        ABORTED
    }
    void addItem(MenuItem menuItem);

    void checkout(Status status);

    Order getCurrentOrder();
}
