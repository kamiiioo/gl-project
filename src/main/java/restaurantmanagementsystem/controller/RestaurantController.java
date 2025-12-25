package restaurantmanagementsystem.controller;
import java.util.Scanner;
import restaurantmanagementsystem.model.MenuComponent;
import restaurantmanagementsystem.model.Order;
import restaurantmanagementsystem.service.CardPayment;
import restaurantmanagementsystem.service.CashPayment;
import restaurantmanagementsystem.service.PaymentService;


public class RestaurantController {

        private Scanner scanner;
        private Order currentOrder;
        private PaymentService paymentService;

        public RestaurantController() {
            scanner = new Scanner(System.in);
            paymentService = new PaymentService();
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
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }


    private void handleShowMenu() {
        MenuComponent menu = MenuComponent.getMenu();
        menu.display();
    }

    private void handleCreateOrder() {
        System.out.println("Creating a new order...");
        currentOrder = new Order();
        while (true) {
            System.out.println("Enter menu item ID to add (0 to finish):");
            int itemId = readInt();
            if (itemId == 0) break;
            currentOrder.addItem(itemId);
            System.out.println("Item added.");
        }
    }

    private void handlePayment() {
        if (currentOrder == null) {
            System.out.println("No active order to pay for.");
            return;
        }

        System.out.println("Choose payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Card");

        int choice = readInt();

        switch (choice) {
            case 1:
                paymentService.setStrategy(new CashPayment());
                break;
            case 2:
                paymentService.setStrategy(new CardPayment());
                break;
            default:
                System.out.println("Invalid payment method.");
                return;
        }

        paymentService.pay(currentOrder);
        currentOrder = null;
    }

}
