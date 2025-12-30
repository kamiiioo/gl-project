package restaurantmanagementsystem.view.console;

import java.util.List;

public class ConsoleOrderView {

    public void afficherCommandes(List<String> commandes) {
        System.out.println("=== COMMANDES ===");

        if (commandes == null || commandes.isEmpty()) {
            System.out.println("Aucune commande en cours.");
            return;
        }

        for (String commande : commandes) {
            System.out.println("- " + commande);
        }
    }

    public void afficherMessage(String message) {
        System.out.println("[INFO] " + message);
    }

    public void afficherErreur(String erreur) {
        System.err.println("[ERREUR] " + erreur);
    }
}