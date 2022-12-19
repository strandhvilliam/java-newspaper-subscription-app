package Models;

import java.util.List;

public interface NewspaperDAO {
    void createNewspaper(String name, String description, double monthlyCost);
    List<Newspaper> readNewspapers();
    void updateNewspaper(Newspaper newspaper);

    void saveNewspapers();
    void loadNewspapers();
}
