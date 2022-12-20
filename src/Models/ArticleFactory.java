package Models;

public class ArticleFactory implements ContentFactory {

    public Article produceContent(String publisherName) {
        String title = "PlaceHolder Article Title";
        String author = "PlaceHolder Author";
        String paragraph = """
                Lorem ipsum dolor sit amet
                Consectetur adipiscing elit. Pellentesque vitae bibendum justo. Nam tristique quis elit id luctus.
                Mauris at blandit neque. Aenean sagittis pellentesque congue. Nullam rhoncus diam sed leo rhoncus lobortis.
                Nunc eleifend in lectus vel vehicula. Cras volutpat purus neque, vel placerat orci convallis id. Curabitur ut pharetra odio, sed
                iaculis nisi. Curabitur pulvinar vestibulum justo, at malesuada dui lobortis vel.
                                
                Aenean eget metus id justo molestie varius blandit eu dolor. Etiam et turpis
                Mauris lacinia rhoncus pretium. Aenean vitae condimentum arcu. Cras vehicula, lacus eget condimentum
                pulvinar, purus turpis congue ligula, non viverra justo nunc id quam. Donec diam libero, rhoncus in ex id, commodo
                aliquet ipsum. Praesent nulla erat, tempor ac nisl eget, bibendum pulvinar dolor.
                Nullam consectetur ut risus eu bibendum. Mauris eget euismod ligula, at imperdiet est. Quisque efficitur
                pharetra tellus vitae rhoncus. Nunc tincidunt, lectus ut euismod efficitur, diam justo ullamcorper odio, imperdiet
                consequat purus lorem tempor est. Integer gravida porta turpis quis pulvinar. Sed felis felis, ultrices vitae
                felis sed, mollis dictum augue""";

        return new Article(publisherName, title, paragraph, author);
    }
}
