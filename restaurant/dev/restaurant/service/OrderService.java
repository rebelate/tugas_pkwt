package dev.restaurant.service;

import dev.restaurant.model.MenuItem;
import dev.restaurant.model.Order;
import dev.restaurant.repository.OrderRepository;
import dev.restaurant.utils.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OrderService implements IOrderService {
    private static final String DATA_FILE_NAME = "logs.txt";
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
    public void checkout(IOrderService.Status status) {
        File dataFile = new File(DATA_FILE_NAME);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException ignored) {
            }
        }
        if (status == IOrderService.Status.ABORTED) return;
        List<MenuItem> uniques = getCurrentOrderDistinctList();
        try (FileWriter writer = new FileWriter(DATA_FILE_NAME, true)) {
            writer.write(Utils.getCurrentDateTime() + "\n");
            writer.write("======================================\n");
            for (MenuItem item : uniques) {
                int count = orderedItemQuantity(item.id());
                writer.write(item.name() + " " + count + " pcs\n");
            }
            writer.write("======================================\n\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

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