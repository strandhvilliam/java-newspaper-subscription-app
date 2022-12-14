package Models;

import java.util.List;

public interface PayBehavior {
    void paySubscription(List<Newspaper> subbedNewspapers, double total);
    double calculateSubscription(List<Newspaper> subbedNewspapers);
    boolean isPremium();
}
