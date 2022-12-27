package service;

import model.MenuItem;
import repository.MenuRepository;

public class MenuService {
    private MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void addMenuItem(MenuItem menuItem) {
        // add menu item to database using menuRepository
    }

    public void updateMenuItem(MenuItem menuItem) {
        // update menu item in database using menuRepository and menuItem
    }

    public void deleteMenuItem(int id) {
        // delete menu item from database using menuRepository and id
    }
}
