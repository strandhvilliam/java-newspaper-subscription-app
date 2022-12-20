package Models;

public class Ad extends Content {

    private String advertiser;

    public Ad(String publisherName, String title, String paragraph, String advertiser) {
        super(publisherName, title, paragraph);
        this.advertiser = advertiser;
    }

    public String getAdvertiser() {
        return advertiser;
    }

    @Override
    public String getHTMLContent() {
        return "<html><h1>" + getTitle() + "</h1>\n<h3>" + getPublisherName() + "</h3>\n<p>" + getParagraph() + "</p>" + "<h3>" + getAdvertiser() + "</h3></html>";
    }
}
