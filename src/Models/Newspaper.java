package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Newspaper implements Observable, Serializable {

    private String name;
    private double monthlyPrice;
    private String description;

    private double totalRevenue;

    private final List<Observer> subscribers;

    private final List<Content> publishedContent;

    private ContentFactory factory;


    public Newspaper(String name, String description, double monthlyPrice) {
        this.name = name;
        this.monthlyPrice = monthlyPrice;
        this.description = description;
        this.totalRevenue = 0;
        this.subscribers = new ArrayList<>();
        this.publishedContent = new ArrayList<>();
        //this.singletonDataHandler = SingletonDataHandler.getInstance();
    }


    @Override
    public void addSubscriber(Observer sub) {
        System.out.println("Newspaper: " + name + " added subscriber");
        System.out.println(this.hashCode());
        subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(Observer sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(String pubName) {
        System.out.println(subscribers.size());
        subscribers.forEach(sub -> sub.update(pubName));
    }

    public void publishContent(boolean isAd) {
        factory = FactoryCreator.getFactory(isAd);
        Content content = factory.produceContent(name);
        System.out.println(content.getTitle());
        publishedContent.add(content);
        notifySubscribers(name);
    }

    public void receivePayment(double amount) {
        totalRevenue += amount;
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
        return name + " - " + description + " - " + "$"+ monthlyPrice;
    }

    public List<Observer> getSubscribers() {
        return subscribers;
    }

    public List<Content> getPublishedContent() {
        return publishedContent;
    }

    public Content getLatestContent() {
        return publishedContent.get(publishedContent.size() - 1);
    }
}
