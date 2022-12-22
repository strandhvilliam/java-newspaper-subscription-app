package Models;

import java.util.List;

public class PremiumPayBehavior implements PayBehavior {

    private final double premiumMarkup = 1.5;

    @Override
    public void paySubscription(List<Newspaper> subbedNewspapers, double total) {
        for (Newspaper newspaper : subbedNewspapers) {
            double price = newspaper.getMonthlyPrice() * premiumMarkup;
            newspaper.receivePayment(price);
            total -= price;
        }
    }

    @Override
    public double calcPayment(List<Newspaper> subbedNewspapers) {
        return subbedNewspapers.stream()
                .mapToDouble(Newspaper::getMonthlyPrice)
                .sum() * premiumMarkup;
    }

    @Override
    public boolean isPremium() {
        return true;
    }

    @Override
    public String toString() {
        return "Premium";
    }
}
