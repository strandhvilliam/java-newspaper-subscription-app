package Models;

public class Article extends Content {
    private String author;


    public Article(int publisherId, String title, String paragraph, String author) {
        super(publisherId, title, paragraph);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String getHTMLContent() {
        return "<html><h1>" + getTitle() + "</h1>\n<h3>" + getAuthor() + "</h3>\n<p>" + getParagraph() + "</p></html>";
    }
}
