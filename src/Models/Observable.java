package Models;

public interface Observable {

    void addSubscriber(Observer subscriber);
    void removeSubscriber(Observer subscriber);
    void notifySubscribers(Content content);
}
