package Models;

public class AdFactory implements ContentFactory {

    @Override
    public Ad createContent(int publisherID) {
        String title = "PlaceHolder Advertisement Title";
        String advertiser = "PlaceHolder Advertiser";
        String paragraph = """
                Lorem ipsum dolor sit amet
                Consectetur adipiscing elit. Pellentesque vitae bibendum justo. Nam tristique quis elit id luctus.
                Mauris at blandit neque. Aenean sagittis pellentesque congue. Nullam rhoncus diam sed leo rhoncus lobortis.
                Nunc eleifend in lectus vel vehicula. Cras volutpat purus neque, vel placerat orci convallis id. Curabitur ut pharetra odio, sed
                iaculis nisi. Curabitur pulvinar vestibulum justo, at malesuada dui lobortis vel.
                """;
        return new Ad(publisherID, title, paragraph, advertiser);
    }
}
