package restaurantmanagementsystem.observer;

public class OrderObserver implements Observer {
    @Override
    public void update(String message){
        System.out.println(message);
    }
}
