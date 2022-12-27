package repository;

import model.Order;

public interface IOrderRepository {
    Order getCurrentOrder();

    void updateCurrentOrder(Order order);

    void clearCurrentOrder();
}
