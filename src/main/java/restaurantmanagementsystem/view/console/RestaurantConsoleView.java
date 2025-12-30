package restaurantmanagementsystem.view.console;

import restaurantmanagementsystem.model.order.Order;
import java.util.List;

public class RestaurantConsoleView {
    
    public void afficherMenuPrincipal() {
        System.out.println("\n=== RESTAURANT MANAGEMENT SYSTEM ===");
        System.out.println("1. Show Menu");
        System.out.println("2. Create Order");
        System.out.println("3. Select Existing Order");
        System.out.println("4. Update Order Status");
        System.out.println("5. Show All Orders");
        System.out.println("6. Payment");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    // ===== MENU DE PAIEMENT =====
    public void afficherMenuPaiement() {
        System.out.println("\n=== MODE DE PAIEMENT ===");
        System.out.println("1. Espèces (5% de réduction)");
        System.out.println("2. Carte (2% de frais)");
        System.out.print("Choix : ");
    }
    
    // ===== MENU STATUT =====
    public void afficherMenuStatut() {
        System.out.println("\n=== STATUT DE COMMANDE ===");
        System.out.println("1. EN PRÉPARATION");
        System.out.println("2. CUISINÉ");
        System.out.println("3. PRÊT");
        System.out.print("Choix : ");
    }

    // ===== COMMANDES EN COURS =====
    public void afficherCommandes(List<Order> commandes) {
        System.out.println("\n=== COMMANDES EN COURS ===");
        if (commandes == null || commandes.isEmpty()) {
            System.out.println("Aucune commande en cours.");
        } else {
            for (Order cmd : commandes) {
                System.out.println(cmd);
            }
        }
    }

    // ===== MESSAGES =====
    public void afficherMessage(String message) {
        System.out.println("\n[INFO] " + message);
    }

    public void afficherErreur(String erreur) {
        System.err.println("\n[ERREUR] " + erreur);
    }
    
    // ===== PROMPTS =====
    public void afficherPrompt(String prompt) {
        System.out.print(prompt + " ");
    }
}