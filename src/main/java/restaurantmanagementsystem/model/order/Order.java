package restaurantmanagementsystem.model.order;


import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item) {
        items.add(item);
        System.out.println("Added " + item.getName() + " to order.");
    }
//fix this after adding the patemnt methods
   /*  public double getTotal() {
        return items.stream().flatMapToDouble(MenuItem::getPrice).sum();
    }*/
    
    public boolean isEmpty() { return items.isEmpty(); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- Order Summary ---\n");
        for (MenuItem item : items) {
            sb.append(item.toString()).append("\n");
        }
      /*  sb.append("Total: $").append(getTotal()); */
        return sb.toString();
    }
}