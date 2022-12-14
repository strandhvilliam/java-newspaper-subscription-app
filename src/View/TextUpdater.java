package View;

import Controllers.App;
import Models.ArticleReader;

public interface TextUpdater {
    void updateTextPane();
    void setArticleReader(ArticleReader articleReader);

    void setController(App app);

}
