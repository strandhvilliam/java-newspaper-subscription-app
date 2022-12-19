package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleReader {

    private final List<Content> articleFeed = new ArrayList<>();

    public void addArticle(Content content) {
        articleFeed.add(content);
    }

    public void sortArticles() {
        Collections.sort(articleFeed);
    }

    public List<String> getHtmlArticlesList() {

        return articleFeed
                .stream()
                .map(k -> k.getHTMLContent())
                .toList();

    }
}
