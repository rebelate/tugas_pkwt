#### pre-structure

```
+-- controller/
|   +-- CashierController.java
|
+-- service/
|   +-- IMenuService.java
|   +-- IOrderService.java
|   +-- MenuService.java
|   +-- OrderService.java
|
+-- model/
|   +-- MenuItem.java
|   +-- Order.java
|
+-- repository/
|   +-- IMenuRepository.java
|   +-- IOrderRepository.java
|   +-- MenuRepository.java
|   +-- OrderRepository.java
|
+-- view/
|   +-- CashierView.java
|   +-- ReceiptView.java
```

**Warning**: Some methods are not optimized (obviously) since this is only prototype