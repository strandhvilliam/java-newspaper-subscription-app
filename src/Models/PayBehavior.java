package Models;

import java.util.List;

public interface PayBehavior {
    void paySubscription(List<Newspaper> subbedNewspapers, double total);
    double calcPayment(List<Newspaper> subbedNewspapers);
    boolean isPremium();
}
