package restaurantmanagementsystem.model.menu;

import java.util.ArrayList;
import java.util.List;

public class Categorie extends MenuComponent {

    private String name;
    private List<MenuComponent> components = new ArrayList<>();

    public Categorie(String name) {
        this.name = name;
    }

    @Override
    public void add(MenuComponent component) {
        components.add(component);
    }

    @Override
    public void remove(MenuComponent component) {
        components.remove(component);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void display() {
        System.out.println("\n== " + name + " ==");
        for (MenuComponent component : components) {
            component.display();
        }
      
    }
}
