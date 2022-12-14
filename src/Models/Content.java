package Models;

import java.time.LocalDate;

public abstract class Content {

    private int publisherID;
    private String title;
    private String paragraph;
    private LocalDate date;

    public Content(int publisherID, String title, String paragraph) {
        this.publisherID = publisherID;
        this.title = title;
        this.paragraph = paragraph;
        this.date = LocalDate.now();
    }


    public abstract String getHTMLContent();

    public int getPublisherID() {
        return publisherID;
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
}