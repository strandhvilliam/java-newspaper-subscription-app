package Models;

import java.io.Serializable;

public class Article extends Content {
    private String author;


    public Article(String publisherName, String title, String paragraph, String author) {
        super(publisherName, title, paragraph);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String getHTMLContent() {
        return "<html><h1>" + getTitle() + "</h1>\n<h3>" + getPublisherName() + "</h3>\n<p>" + getParagraph() + "</p>" + "<h3>" + getAuthor() + "</h3></html>";
    }
}
