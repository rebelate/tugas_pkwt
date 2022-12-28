package dev.restaurant.repository;

import dev.restaurant.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository implements IMenuRepository {
    private List<MenuItem> menus = new ArrayList<>();

    @Override
    public MenuItem getById(int id) {
        return menus.stream().filter(menuItem -> menuItem.id() == id).findFirst().orElse(null);
    }

    @Override
    public List<MenuItem> getAll() {
        return menus;
    }

    @Override
    public void addMenu(MenuItem menuItem) {
        menus.add(menuItem);
    }

    @Override
    public void setMenus(List<MenuItem> menus) {
        this.menus = menus;
    }
}
