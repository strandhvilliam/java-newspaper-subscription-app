package Controllers;

import Models.*;
import View.ClientGUI;

import java.util.List;

public class UserController {
    private final ArticleReader articleReader;
    private final User user;
    private final NewspaperDAO newspaperDAO;
    private final ClientGUI clientGUI;

    public UserController(ClientGUI clientGUI, User user, NewspaperDAO newspaperDAO) {
        this.clientGUI = clientGUI;
        this.user = user;
        this.newspaperDAO = newspaperDAO;
        this.articleReader = new ArticleReader();
        clientGUI.setGUIController(this);
        user.setUserApp(this);
    }

    public void update(String pubName) {
        Newspaper newspaper = newspaperDAO.readNewspapers()
                .stream()
                .filter(n -> n.getName().equals(pubName))
                .findFirst()
                .get();
        Content content = newspaper.getLatestContent();
        if (content instanceof Ad) {
            if (!user.getPayBehavior().isPremium()) {
               articleReader.addArticle(content);
            }
        } else {
            articleReader.addArticle(content);
        }
        clientGUI.updateArticleFeed(articleReader.getHtmlArticlesList());
    }

    public void subscribe(Newspaper n) {
        n.addSubscriber(user);
        user.addSubscription(n.getName());
    }

    public void unsubscribe(Newspaper n) {
        n.removeSubscriber(user);
        user.removeSubscription(n.getName());
    }

    public void changePayBehavior() {
        if (user.getPayBehavior() instanceof StandardPayBehavior) {
            user.setPayBehavior(new PremiumPayBehavior());
        } else {
            user.setPayBehavior(new StandardPayBehavior());
        }
    }

    public List<Newspaper> getSubbedNewspapers() {
        return newspaperDAO.readNewspapers()
                .stream()
                .filter(n -> user.getSubscribedPaperNames().contains(n.getName()))
                .toList();
    }

    public List<Newspaper> getUnsubbedNewspapers() {
        return newspaperDAO.readNewspapers()
                .stream()
                .filter(n -> !user.getSubscribedPaperNames().contains(n.getName()))
                .toList();
    }

    public double getMonthlyCost() {
        return user.getPayBehavior().calcPayment(getSubbedNewspapers());
    }

    public User getUser() {
        return user;
    }

    public void paySubscription() {
        getSubbedNewspapers().forEach(n -> n.receivePayment(n.getMonthlyPrice()));
    }
}
