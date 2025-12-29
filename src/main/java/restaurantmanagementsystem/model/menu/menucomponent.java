package restaurantmanagementsystem.model.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuComponent {
    private static MenuComponent instance;
    
    public MenuComponent() {
      
    }
    
    public static MenuComponent getMenu() {
        if (instance == null) {
            instance = new MenuComponent();
        }
        return instance;
    }
    
    public void display() {
        System.out.println("=== RESTAURANT MENU ===");
        System.out.println("1. Pizza - $12.99");
        System.out.println("2. Burger - $10.99");
        System.out.println("3. Salad - $8.99");
        System.out.println("4. Pasta - $11.99");
        System.out.println("5. Sandwich - $7.99");
        System.out.println("=======================");
    }
      public void add(MenuComponent component) {
        throw new UnsupportedOperationException();
    }
    
    public void remove(MenuComponent component) {
        throw new UnsupportedOperationException();
    }
    
    public String getName() {
        throw new UnsupportedOperationException();
    }
    
    public double getPrice() {
        throw new UnsupportedOperationException();
    }

}
