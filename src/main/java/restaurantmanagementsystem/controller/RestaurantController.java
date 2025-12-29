package restaurantmanagementsystem.controller;

import java.util.Scanner;
import restaurantmanagementsystem.model.menu.MenuComponent; // From model
import restaurantmanagementsystem.model.menu.MenuItem; // From model.menu
import restaurantmanagementsystem.model.core.RestaurantManager;
import restaurantmanagementsystem.model.order.Order;
import restaurantmanagementsystem.model.order.OrderStatus;
import restaurantmanagementsystem.model.payment.CardPayment; // Note: "payment" NOT "payement"
import restaurantmanagementsystem.model.payment.CashPayment; // Note: "payment" NOT "payement"

public class RestaurantController {

        private Scanner scanner;
        private int currentOrderId = -1;
        private RestaurantManager restaurantManager;

        public RestaurantController() {
            scanner = new Scanner(System.in);
            restaurantManager = RestaurantManager.getInstance();
        }

        public void start() {
            boolean running = true;

            while (running) {
                showMainMenu();
                int choice = readInt();

                switch (choice) {
                    case 1:
                        handleShowMenu();
                        break;
                    case 2:
                        handleCreateOrder();
                        break;
                    case 3:
                        handlePayment();
                        break;
                    case 4:
                        handleUpdateStatus();
                        break;
                    case 5:
                        handleSelectOrder();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }

            scanner.close();
        }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number:");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void showMainMenu() {
        System.out.println("=== RESTAURANT MANAGEMENT SYSTEM ===");
        System.out.println("1. Show Menu");
        System.out.println("2. Create Order");
        System.out.println("3. Payment");
        System.out.println("4. Update Order Status");
        System.out.println("5. Select Existing Order");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }


    private void handleShowMenu() {
        MenuComponent menu = MenuComponent.getMenu();
        menu.display();
    }

    private void handleCreateOrder() {
        System.out.println("Creating a new order...");
        currentOrderId = restaurantManager.createOrder();
        while (true) {
            System.out.println("Enter menu item ID to add (0 to finish):");
            int itemId = readInt();
            if (itemId == 0) break;
            // Simulating item lookup for the new Order class structure
            MenuItem item = new MenuItem("Item #" + itemId, 10.0);
            restaurantManager.addItemToOrder(currentOrderId, item);
            System.out.println("Item added.");
        }
    }

    private void handlePayment() {
        if (currentOrderId == -1) {
            System.out.println("No active order to pay for.");
            return;
        }

        System.out.println("Choose payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Card");

        int choice = readInt();

        switch (choice) {
            case 1:
                restaurantManager.processPayment(currentOrderId, new CashPayment());
                break;
            case 2:
                restaurantManager.processPayment(currentOrderId, new CardPayment());
                break;
            default:
                System.out.println("Invalid payment method.");
                return;
        }
        
        System.out.println("Payment processed.");
        currentOrderId = -1; // Reset active order
    }

    private void handleUpdateStatus() {
        if (currentOrderId == -1) {
            System.out.println("No active order.");
            return;
        }
        Order currentOrder = restaurantManager.getOrderById(currentOrderId);
        System.out.println(currentOrder);
        System.out.println("Select new status:");
        System.out.println("1. UNDER_PREPARATION");
        System.out.println("2. COOKED");
        System.out.println("3. PREPARED");
        
        int choice = readInt();
        switch (choice) {
            case 1: restaurantManager.updateOrderStatus(currentOrderId, OrderStatus.UNDER_PREPARATION); break;
            case 2: restaurantManager.updateOrderStatus(currentOrderId, OrderStatus.COOKED); break;
            case 3: restaurantManager.updateOrderStatus(currentOrderId, OrderStatus.PREPARED); break;
            default: System.out.println("Invalid status selection.");
        }
    }

    private void handleSelectOrder() {
        System.out.println("Enter Order ID to select:");
        int id = readInt();
        if (restaurantManager.getOrderById(id) != null) {
            currentOrderId = id;
            System.out.println("Order #" + id + " is now active.");
        } else {
            System.out.println("Order not found.");
        }
    }
}
