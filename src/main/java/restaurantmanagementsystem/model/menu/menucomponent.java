package restaurantmanagementsystem.model.menu;

public abstract class MenuComponent {

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

    public void display() {
        throw new UnsupportedOperationException();
    }
}
