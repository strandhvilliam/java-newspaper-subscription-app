package Controllers;

import Models.*;
import View.TextUpdater;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class App implements Observer {


    private final ArticleReader articleReader;
    private final JFrame mainGUI;

    private final TextUpdater client;

    private PayBehavior payBehavior;
    private List<Integer> subbedNewspapersID = new ArrayList<>();
    private SingletonDataHandler singletonDataHandler = SingletonDataHandler.getInstance();


    public App(JFrame mainGUI, TextUpdater client) {
        this.mainGUI = mainGUI;
        this.client = client;
        this.articleReader = new ArticleReader();
        this.payBehavior = new StandardPayBehavior();
        client.setArticleReader(articleReader);
        client.setController(this);
    }

    @Override
    public void update(Content content) {
        //check paybehavior and if ad
        articleReader.addArticle(content.getHTMLContent());
        client.updateTextPane();
    }

    public void subscribe(int n) {
        Newspaper newspaper = singletonDataHandler.getSingleNewspaper(n);
        if (newspaper != null) {
            newspaper.addSubscriber(this);
            subbedNewspapersID.add(n);
        }
    }

    public void unsubscribe(int n) {
        Newspaper newspaper = singletonDataHandler.getSingleNewspaper(n);
        if (newspaper != null) {
            newspaper.removeSubscriber(this);
            subbedNewspapersID.add(n);
        }
    }

    public void changePayBehavior() {
        if (payBehavior instanceof StandardPayBehavior) {
            payBehavior = new PremiumPayBehavior();
        } else {
            payBehavior = new StandardPayBehavior();
        }
    }
}
