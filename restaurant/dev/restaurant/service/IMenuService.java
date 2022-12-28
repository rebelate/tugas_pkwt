package dev.restaurant.service;

import dev.restaurant.model.MenuItem;

import java.util.List;

public interface IMenuService {
    void setMenu();

    MenuItem getById(int menuItemId);

    List<MenuItem> getAll();
}
