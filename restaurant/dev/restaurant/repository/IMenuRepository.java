package dev.restaurant.repository;

import dev.restaurant.model.MenuItem;

import java.util.List;

public interface IMenuRepository {
    MenuItem getById(int id);

    List<MenuItem> getAll();

    void addMenu(MenuItem menuItem);

    void setMenus(List<MenuItem> menus);
}
