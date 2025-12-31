package restaurantmanagementsystem.model.core;

import java.util.HashMap;
import java.util.Map;
import restaurantmanagementsystem.model.menu.MenuItem;
import restaurantmanagementsystem.model.order.Order;
import restaurantmanagementsystem.model.order.OrderStatus;
import restaurantmanagementsystem.model.payment.PaymentStrategy;
/**
 * Gestionnaire central du restaurant (Singleton).
 * Garantit une seule instance pour gérer l'état global.
 * 
 * Design Pattern: Singleton
 * 
 * Responsabilités:
 * - Gérer toutes les commandes
 * - Coordonner les opérations de paiement
 * - Maintenir l'état des commandes
 * - Appliquer les règles métier
 */
public class RestaurantManager {
    private static RestaurantManager instance;
    
    private Map<Integer, Order> orders;
    private Map<Integer, OrderStatus> orderStatuses; 
    private int nextOrderId;
    

    private RestaurantManager() {
        orders = new HashMap<>();
        orderStatuses = new HashMap<>();
        nextOrderId = 1;
    }

    public static synchronized RestaurantManager getInstance() {
        if (instance == null) {
            instance = new RestaurantManager();
        }
        return instance;
    }

    public int createOrder() {
        Order order = new Order();
        int id = nextOrderId++;
        orders.put(id, order);
        orderStatuses.put(id, OrderStatus.UNDER_PREPARATION);
        return id;
    }

    public void removeOrder(int orderId) {
        orders.remove(orderId);
        orderStatuses.remove(orderId);
    }


    public Order getOrderById(int id) {
        return orders.get(id);
    }

    public void addItemToOrder(int orderId, MenuItem item) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.addItem(item);
        }
    }

    public void updateOrderStatus(int orderId, OrderStatus status) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setStatus(status);
            orderStatuses.put(orderId, status);
        }
    }

    public boolean processPayment(int orderId, PaymentStrategy strategy) {
        OrderStatus currentStatus = orderStatuses.get(orderId);
        Order order = orders.get(orderId);
        
        if (order == null) {
           
            return false;
        }
        
        if (currentStatus != OrderStatus.PREPARED) {
            
            return false;
        }

        double total = order.getTotal();
        strategy.pay(total);
        return true;
    }
}
