package Controllers;

import Models.Article;
import Models.Newspaper;
import Models.NewspaperFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingletonDataHandler {



    private List<Newspaper> newspapers = new ArrayList<>();

    private Map<Integer, List<Article>> articlesMap = new HashMap<>();

    private static final SingletonDataHandler instance = new SingletonDataHandler();

    private static final NewspaperFactory newspaperFactory = new NewspaperFactory();

    private SingletonDataHandler() {

    }

    public static SingletonDataHandler getInstance() {

        return instance;
    }

    public void addNewspaper(String name, String description, double monthlyPrice) {
        newspapers.add(newspaperFactory.createNewspaper(name, description, monthlyPrice));
    }

    public void addArticle(int newspaperId, Article article) {
        if (articlesMap.containsKey(newspaperId)) {
            articlesMap.get(newspaperId).add(article);
        } else {
            List<Article> articles = new ArrayList<>();
            articles.add(article);
            articlesMap.put(newspaperId, articles);
        }
    }

    public List<Newspaper> getNewspapers() {
        return newspapers;
    }

    public Newspaper getSingleNewspaper(int id) {
        return newspapers.stream()
                .filter(newspaper -> newspaper.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void removeNewspaper(Newspaper newspaper) {
        newspapers.remove(newspaper);
    }

}
