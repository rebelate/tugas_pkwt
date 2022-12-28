package dev.restaurant.service;

import dev.restaurant.model.MenuItem;
import dev.restaurant.repository.MenuRepository;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class MenuService implements IMenuService {
    private static final String DATA_FILE_NAME = "menus.txt";
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
        setMenu();
    }

    private List<MenuItem> getDefaultMenu() {
        return Arrays.asList(
                new MenuItem(1, "Ayam Geprek", "makanan", 14000),
                new MenuItem(2, "Lele", "makanan", 10000),
                new MenuItem(3, "Nasi Putih", "makanan", 4000),
                new MenuItem(4, "Sate Usus", "makanan", 3000),
                new MenuItem(5, "Sate Kulit", "makanan", 3000),
                new MenuItem(6, "Sate Ati Ampela", "makanan", 3000),
                new MenuItem(7, "Sate Telor Puyuh", "makanan", 4000),
                new MenuItem(8, "Tahu", "makanan", 1500),
                new MenuItem(9, "Tempe", "makanan", 1500),


                new MenuItem(1, "Teh Manis Panas", "minuman", 3000),
                new MenuItem(2, "Teh Manis Dingin", "minuman", 3000),
                new MenuItem(3, "Fanta", "minuman", 5000),
                new MenuItem(4, "Sprite", "minuman", 5000),
                new MenuItem(5, "Coca Cola", "minuman", 5000),
                new MenuItem(6, "Air Mineral", "minuman", 2500),
                new MenuItem(7, "Kopi Hitam", "makanan", 3000),

                new MenuItem(1, "(Paket A) Ayam Geprek + Nasi + Es Teh Manis", "paket", 20000),
                new MenuItem(2, "(Paket B) Ayam Geprek + Nasi + Tahu + Tempe + Es Teh Manis", "paket", 23000),
                new MenuItem(3, "(Paket C) Ayam Geprek + Nasi + Tahu + Tempe + Sate(Kulit/Usus/Ati Ampela) + Es Teh Manis", "paket", 25000)
        );
    }

    @Override
    public void setMenu() {
        File dataFile = new File(DATA_FILE_NAME);
        if (dataFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String type = parts[2];
                    int price = Integer.parseInt(parts[3]);
                    MenuItem menuItem = new MenuItem(id, name, type, price);
                    menuRepository.addMenu(menuItem);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            List<MenuItem> menus = getDefaultMenu();
            menuRepository.setMenus(menus);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_NAME))) {
                for (MenuItem menuItem : menus) {
                    writer.write(menuItem.id() + "," + menuItem.name() + "," + menuItem.type() + "," + menuItem.price());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MenuItem getById(int menuItemId) {
        return menuRepository.getById(menuItemId);
    }

    @Override
    public List<MenuItem> getAll() {
        return menuRepository.getAll();
    }
}
