package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleReader {

    private final List<Content> articleFeed = new ArrayList<>();

    public void addArticle(Content content) {
        articleFeed.add(0, content);
    }

    public void sortArticles() {
        Collections.sort(articleFeed);
    }

    public List<String> getHtmlArticlesList() {

        articleFeed.forEach(content -> System.out.println(content.getPublisherName()));

        return articleFeed
                .stream()
                .map(k -> k.getHTMLContent())
                .toList();
    }
}
