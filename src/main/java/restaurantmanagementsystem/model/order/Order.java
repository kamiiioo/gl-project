package restaurantmanagementsystem.model.order;


import java.util.ArrayList;
import java.util.List;
import restaurantmanagementsystem.model.MenuItem;

public class Order implements Observable {
    private List<OrderItem> items = new ArrayList<>();
    private OrderStatus status;
    private List<Observer> observers = new ArrayList<>();
    
    public Order() {
        this.status = OrderStatus.PENDING;
    }
    
    @Override
    public void addObserver(Observer observer) { 
        observers.add(observer); 
    }

    @Override
    public void removeObserver(Observer observer) { 
        observers.remove(observer); 
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
    
    public void addItem(MenuItem item) {
        // Wraps the menu item into an OrderItem (Detail)
        items.add(new OrderItem(item, 1));
        System.out.println("Added " + item.getName() + " to order.");
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
        notifyObservers("Order status changed to: " + status);
    }

    public double getTotal() {
        return items.stream().mapToDouble(OrderItem::getSubtotal).sum();
    }
    
    public boolean isEmpty() { return items.isEmpty(); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- Order Summary (Status: ").append(status).append(") ---\n");
        for (OrderItem detail : items) {
            sb.append(detail.getItem().getName())
              .append(" x").append(detail.getQuantity())
              .append(" - $").append(detail.getSubtotal()).append("\n");
        }
        sb.append("Total: $").append(getTotal());
        return sb.toString();
    }
}
