package repository;

import model.MenuItem;

import java.util.List;

public final class MenuRepository implements IMenuRepository {
    private final List<MenuItem> menus;

    public MenuRepository(List<MenuItem> menus) {
        this.menus = menus;
    }

    @Override
    public MenuItem getById(int id) {
        return menus.stream().filter(menuItem -> menuItem.id() == id).findFirst().orElse(null);
    }

    @Override
    public List<MenuItem> getAll() {
        return menus;
    }

}
