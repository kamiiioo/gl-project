package restaurantmanagementsystem.model.menu;

import java.util.ArrayList;
import java.util.List;

public class Categorie extends MenuComponent {

    private String name;
    private List<MenuComponent> components = new ArrayList<>();

    public Categorie(String name) {
        this.name = name;
    }


    public void add(MenuComponent component) {
        components.add(component);
    }
  
    public void remove(MenuComponent component) {
        components.remove(component);
    }
  
    public String getName() {
        return name;
    }

   
    public void display() {
        System.out.println("\n== " + name + " ==");
        for (MenuComponent component : components) {
            component.display();
        }
      
    }
}
