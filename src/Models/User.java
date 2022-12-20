package Models;

import Controllers.UserController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Observer {


    private String name;
    private String email;

    private PayBehavior payBehavior;

    private List<String> subscribedPaperNames;

    private UserController userController;

    public User(String name, String email, PayBehavior payBehavior) {
        this.name = name;
        this.email = email;
        this.payBehavior = payBehavior;
        this.subscribedPaperNames = new ArrayList<>();
    }

    public void addSubscription(String paperName) {
        subscribedPaperNames.add(paperName);
    }

    public void removeSubscription(String paperName) {
        subscribedPaperNames.remove(paperName);
    }

    public void setPayBehavior(PayBehavior payBehavior) {
        this.payBehavior = payBehavior;
    }

    public List<String> getSubscribedPaperNames() {
        return subscribedPaperNames;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public PayBehavior getPayBehavior() {
        return payBehavior;
    }

    public void setUserApp(UserController userController) {
        this.userController = userController;
    }

    @Override
    public void update(String pubName) {
        userController.update(pubName);
    }
}
