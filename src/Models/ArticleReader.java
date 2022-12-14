package Models;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ArticleReader {

    private List<String> articleList = new ArrayList<>();

    public ArticleReader() {

    }
    public void addArticle(String htmlContent) {
        articleList.add(htmlContent);
    }

    public List<String> getArticleList() {
        return articleList;
    }
}
