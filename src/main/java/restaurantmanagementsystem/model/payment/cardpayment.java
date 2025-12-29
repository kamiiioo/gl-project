package restaurantmanagementsystem.model.payment;

import java.text.DecimalFormat;

public class CardPayment implements PaymentStrategy {

    private static final double CARD_FEE = 0.02; // 2%
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    @Override
    public double calculateTotal(double amount) {
        return amount + (amount * CARD_FEE);
    }

    @Override
    public void pay(double amount) {
        double total = calculateTotal(amount);

        System.out.println("══════════════════════════════");
        System.out.println("💳 MODE DE PAIEMENT : CARTE");
        System.out.println("══════════════════════════════");
        System.out.println("Montant initial : " + DF.format(amount) + " DA");
        System.out.println("Frais carte (2%) : " + DF.format(amount * CARD_FEE) + " DA");
        System.out.println("➡️ Montant final : " + DF.format(total) + " DA");
        System.out.println("Paiement effectué avec succès ✔");
        System.out.println("══════════════════════════════");
    }

    @Override
    public String getPaymentName() {
        return "Paiement par carte";
    }
}

