package restaurantmanagementsystem.model.payment;


public interface PaymentStrategy {

    /**
     * Calcule le montant final à payer
     */
    double calculateTotal(double amount);

    /**
     * Exécute le paiement
     */
    void pay(double amount);

    /**
     * Nom du mode de paiement
     */
    String getPaymentName();
}
