package Models;

public class NewspaperFactory {

    public static int amountOfNewspapers = 0;

    public Newspaper createNewspaper(String name, String description, double monthlyPrice) {
        return new Newspaper(name, description, monthlyPrice, amountOfNewspapers++);
    }


}
