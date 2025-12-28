package restaurantmanagementsystem.model.menu;

public class Plat extends MenuComponent {

    private String name;
    private double price;

    public Plat(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void display() {
        System.out.println("- " + name + " : " + price + " DA");
    }
}
