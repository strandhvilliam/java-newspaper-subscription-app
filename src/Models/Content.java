package Models;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Content implements Serializable, Comparable<Content> {

    private String publisherName;
    private String title;
    private String paragraph;
    private LocalDate date;

    public Content(String publisherName, String title, String paragraph) {
        this.publisherName = publisherName;
        this.title = title;
        this.paragraph = paragraph;
        this.date = LocalDate.now();
    }


    public abstract String getHTMLContent();

    public String getPublisherName() {
        return publisherName;
    }

    public String getTitle() {
        return title;
    }

    public String getParagraph() {
        return paragraph;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public int compareTo(Content o) {
        return this.date.compareTo(o.getDate());
    }
}