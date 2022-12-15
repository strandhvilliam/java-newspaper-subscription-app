package Models;

import Controllers.SingletonDataHandler;

import java.util.ArrayList;
import java.util.List;

public class Newspaper implements Observable {

    private String name;
    private double monthlyPrice;
    private String description;

    private double totalRevenue;

    private final int id;
    private List<Observer> subscribers;

    private ContentFactory factory;
    private SingletonDataHandler singletonDataHandler;

    public Newspaper(String name, String description, double monthlyPrice, int id) {
        this.name = name;
        this.monthlyPrice = monthlyPrice;
        this.description = description;
        this.totalRevenue = 0;
        this.id = id;
        this.subscribers = new ArrayList<>();
        this.singletonDataHandler = SingletonDataHandler.getInstance();
    }


    @Override
    public void addSubscriber(Observer sub) {
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(Observer sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Content content) {
        subscribers.forEach(sub -> sub.update(content));
    }

    public void publishContent(boolean isAd) {

        factory = FactoryCreator.getFactory(isAd);

        Content content = factory.produceContent(id);
        notifySubscribers(content);
        if (!isAd) {
            singletonDataHandler.addArticle(id, (Article) content); //add article to data singleton only if not ad
        }
    }

    public void receivePayment(double amount) {
        totalRevenue += amount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }


    @Override
    public String toString() {
        return name + " - " + description + " - " + "$"+ monthlyPrice + "ID: " + id;
    }
}
