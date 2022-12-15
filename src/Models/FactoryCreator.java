package Models;

public class FactoryCreator {
    public static ContentFactory getFactory(boolean isAd) {
        if (isAd) {
            return new AdFactory();
        } else {
            return new ArticleFactory();
        }
    }
}
