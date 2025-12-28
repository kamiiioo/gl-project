package org.example;




import restaurantmanagementsystem.controller.RestaurantController;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Restaurant Management System ===");
        System.out.println("Starting application...\n");
        
        RestaurantController controller = new RestaurantController();
        controller.start();
    }
}