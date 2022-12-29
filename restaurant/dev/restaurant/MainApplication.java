package dev.restaurant;

import dev.restaurant.controller.CashierController;
import dev.restaurant.repository.MenuRepository;
import dev.restaurant.repository.OrderRepository;
import dev.restaurant.service.MenuService;
import dev.restaurant.service.OrderService;
import dev.restaurant.view.CashierView;

public class MainApplication {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepository();
        MenuRepository menuRepository = new MenuRepository();

        MenuService menuService = new MenuService(menuRepository);
        OrderService orderService = new OrderService(orderRepository);
        CashierController cashierController = new CashierController(orderService, menuService);
        cashierController.handleStartCashierApp();
    }
}
