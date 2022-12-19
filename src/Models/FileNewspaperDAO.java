package Models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileNewspaperDAO implements NewspaperDAO {
    private final File dbFile;

    private List<Newspaper> newspapersList;
    public FileNewspaperDAO(String newspaperDBFilePath) {
        this.dbFile = new File(newspaperDBFilePath);
        loadNewspapers();
    }

    public void createNewspaper(String name, String description, double monthlyPrice) {
        Newspaper n = new Newspaper(name, description, monthlyPrice);
        newspapersList.add(n);
    }

    public List<Newspaper> readNewspapers() {
        return newspapersList;
    }

    public void updateNewspaper(Newspaper newspaper) {

    }

    @Override
    public void saveNewspapers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dbFile))) {

            for (Newspaper newspaper : newspapersList) {
                bw.write(newspaper.getName() + "\n" + newspaper.getDescription() + "\n" + newspaper.getMonthlyPrice() + "\n");
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadNewspapers() {
        List<Newspaper> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dbFile))) {

            String line;
            while((line = br.readLine()) != null) {
                String name = line;
                String description = br.readLine();
                double monthlyPrice = Double.parseDouble(br.readLine());
                Newspaper n = new Newspaper(name, description, monthlyPrice);
                list.add(n);
                br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newspapersList = new ArrayList<>(list);
    }


}
