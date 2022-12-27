package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Order(List<MenuItem> items, double totalCost) {

    public Order addItem(MenuItem item) {
        List<MenuItem> newItems = new ArrayList<>(items);
        newItems.add(item);
        return new Order(newItems, totalCost + item.price());
    }

    public Order removeItem(MenuItem item) {
        List<MenuItem> newItems = new ArrayList<>(items);
        newItems.remove(item);
        return new Order(newItems, totalCost - item.price());
    }

    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double getTotalCost() {
        return totalCost;
    }
}
