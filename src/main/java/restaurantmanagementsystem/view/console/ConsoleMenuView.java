package restaurantmanagementsystem.view.console;

import java.util.List;

public class ConsoleMenuView {

    public void afficherMenu(List<String> menuItems) {
        System.out.println("=== MENU ===");
        for (String item : menuItems) {
            System.out.println(item);
        }
    }

    public void afficherMessage(String message) {
        System.out.println("[INFO] " + message);
    }
    
    public void afficherErreur(String erreur) {
        System.err.println("[ERREUR] " + erreur);
    }
}