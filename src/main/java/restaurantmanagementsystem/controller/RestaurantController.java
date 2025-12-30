package restaurantmanagementsystem.controller;

import java.util.Scanner;
import restaurantmanagementsystem.model.menu.MenuComponent; 
import restaurantmanagementsystem.model.menu.MenuItem; 
import restaurantmanagementsystem.model.core.RestaurantManager;
import restaurantmanagementsystem.model.order.Order;
import restaurantmanagementsystem.model.order.OrderStatus;
import restaurantmanagementsystem.model.payment.CardPayment; 
import restaurantmanagementsystem.model.payment.CashPayment;
import restaurantmanagementsystem.view.console.RestaurantConsoleView; 
import restaurantmanagementsystem.view.console.ConsoleOrderView;
import restaurantmanagementsystem.view.console.ConsoleMenuView;
import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur principal de l'application restaurant.
 * Gère le flux de l'application et coordonne les interactions
 * entre la vue et le modèle.
 * 
 * Responsabilités:
 * - Afficher le menu principal
 * - Gérer la création des commandes
 * - Traiter les paiements
 * - Mettre à jour les statuts des commandes
 */

public class RestaurantController {
    private Scanner scanner;
    private int currentOrderId = -1;
    private RestaurantManager restaurantManager;
    private RestaurantConsoleView consoleView;
    private ConsoleOrderView orderView;
    private ConsoleMenuView menuView;

    public RestaurantController() {
        scanner = new Scanner(System.in);
        restaurantManager = RestaurantManager.getInstance();
        consoleView = new RestaurantConsoleView();
        orderView = new ConsoleOrderView();
        menuView = new ConsoleMenuView();
    }

   public void start() {
        boolean running = true;
        while (running) {
            consoleView.afficherMenuPrincipal();
            int choice = readInt();
            
            switch (choice) {
                case 1: handleShowMenu(); break;
                case 2: handleCreateOrder(); break;
                case 3: handleSelectOrder(); break;
                case 4: handleUpdateStatus(); break;
                case 5: handleShowAllOrders(); break;  // Fixed: This should show orders, not payment
                case 6: handlePayment(); break; 
                case 0: 
                    running = false; 
                    consoleView.afficherMessage("Au revoir!");
                    break;
                default: 
                    consoleView.afficherErreur("Choix invalide.");
            }
        }
        scanner.close();
    }


    private int readInt() {
        while (!scanner.hasNextInt()) {
            consoleView.afficherErreur("Veuillez entrer un nombre valide:");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void handleShowMenu() {
          MenuComponent menu = MenuComponent.getMenu();
        menu.display();
    }

    private void handleShowAllOrders() {
          System.out.println("\n📋 LISTE DES COMMANDES");
    System.out.println("======================");
    
    // Check first 10 possible order IDs
    for (int i = 1; i <= 10; i++) {
        Order order = restaurantManager.getOrderById(i);
        if (order != null) {
            System.out.println("\n• Commande #" + i);
            System.out.println("  " + order.toString().replace("\n", "\n  "));
        }
    }
    
    if (currentOrderId != -1) {
        System.out.println("\n📌 Commande active: #" + currentOrderId);
    } else {
        System.out.println("\nAucune commande active.");
    }
    }

    private void handleCreateOrder() {
        consoleView.afficherMessage("Création d'une nouvelle commande...");
        currentOrderId = restaurantManager.createOrder();
        
        while (true) {
            consoleView.afficherPrompt("Entrez l'ID de l'article à ajouter (0 pour terminer):");
            int itemId = readInt();
            if (itemId == 0) break;
            
            // Create a simple menu item based on ID
            MenuItem item = createMenuItemById(itemId);
            if (item != null) {
                restaurantManager.addItemToOrder(currentOrderId, item);
                consoleView.afficherMessage("Article ajouté.");
            } else {
                consoleView.afficherErreur("ID d'article invalide.");
            }
        }
    }

    private MenuItem createMenuItemById(int itemId) {
        switch (itemId) {
            case 1: return new MenuItem("Pizza", 500.0);
            case 2: return new MenuItem("Burger", 400.0);
            case 3: return new MenuItem("Salad", 300.0);
            case 4: return new MenuItem("Pasta", 800.0);
            case 5: return new MenuItem("Sandwich", 350.0);
            default: return null;
        }
    }

    private void handlePayment() {
        if (currentOrderId == -1) {
            consoleView.afficherErreur("Aucune commande active à payer.");
            return;
        }

        consoleView.afficherMenuPaiement();
        int choice = readInt();
        boolean success = false;
        
        switch (choice) {
            case 1:
                success = restaurantManager.processPayment(currentOrderId, new CashPayment());
                break;
            case 2:
                success = restaurantManager.processPayment(currentOrderId, new CardPayment());
                break;
            default:
                consoleView.afficherErreur("Mode de paiement invalide.");
                return;
        }
        
        if (success) {
            consoleView.afficherMessage("Paiement traité avec succès.");
            currentOrderId = -1;
        } else {
            consoleView.afficherErreur("Le paiement n'a pas pu être traité.");
        }
    }

    private void handleUpdateStatus() {
        if (currentOrderId == -1) {
            consoleView.afficherErreur("Aucune commande active.");
            return;
        }
        
        Order currentOrder = restaurantManager.getOrderById(currentOrderId);
        if (currentOrder != null) {
            consoleView.afficherMessage(currentOrder.toString());
        }
        
        consoleView.afficherMenuStatut();
        
        int choice = readInt();
        switch (choice) {
            case 1: 
                restaurantManager.updateOrderStatus(currentOrderId, OrderStatus.UNDER_PREPARATION);
                consoleView.afficherMessage("Statut mis à jour: EN PRÉPARATION");
                break;
            case 2: 
                restaurantManager.updateOrderStatus(currentOrderId, OrderStatus.COOKED);
                consoleView.afficherMessage("Statut mis à jour: CUISINÉ");
                break;
            case 3: 
                restaurantManager.updateOrderStatus(currentOrderId, OrderStatus.PREPARED);
                consoleView.afficherMessage("Statut mis à jour: PRÊT");
                break;
            default: 
                consoleView.afficherErreur("Sélection de statut invalide.");
        }
    }

    private void handleSelectOrder() {
        consoleView.afficherPrompt("Entrez l'ID de la commande à sélectionner:");
        int id = readInt();
        
        if (restaurantManager.getOrderById(id) != null) {
            currentOrderId = id;
            consoleView.afficherMessage("Commande #" + id + " est maintenant active.");
        } else {
            consoleView.afficherErreur("Commande non trouvée.");
        }
    }
}