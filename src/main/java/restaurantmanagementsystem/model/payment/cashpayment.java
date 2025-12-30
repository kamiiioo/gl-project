package restaurantmanagementsystem.model.payment;


import java.text.DecimalFormat;

public class CashPayment implements PaymentStrategy {

    private static final double DISCOUNT = 0.05; // 5%
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    @Override
    public double calculateTotal(double amount) {
        return amount - (amount * DISCOUNT);
    }

    @Override
    public void pay(double amount) {
        double total = calculateTotal(amount);

        System.out.println("══════════════════════════════");
        System.out.println("MODE DE PAIEMENT : ESPÈCES");
        System.out.println("══════════════════════════════");
        System.out.println("Montant initial : " + DF.format(amount) + " DA");
        System.out.println("Réduction cash (5%) : -" + DF.format(amount * DISCOUNT) + " DA");
        System.out.println("Montant final : " + DF.format(total) + " DA");
        System.out.println("Paiement effectué avec succès ✔");
        System.out.println("══════════════════════════════");
    }

    @Override
    public String getPaymentName() {
        return "Paiement en espèces";
    }
}
