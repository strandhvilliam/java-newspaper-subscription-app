package Models;

import java.util.List;

public class StandardPayBehavior implements PayBehavior {

    public void paySubscription(List<Newspaper> subbedNewspapers, double total) {
        for (Newspaper newspaper : subbedNewspapers) {
            double price = newspaper.getMonthlyPrice();
            newspaper.receivePayment(price);
            total -= price;
        }
    }

    public double calcPayment(List<Newspaper> subbedNewspapers) {
        return subbedNewspapers.stream()
            .mapToDouble(Newspaper::getMonthlyPrice)
            .sum();
    }

    public boolean isPremium() {
        return false;
    }

    @Override
    public String toString() {
        return "Standard";
    }
}
