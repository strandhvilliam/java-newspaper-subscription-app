package Models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUserDAO implements UserDAO {
    private final File dbFile;
    private List<User> usersList;

    public FileUserDAO(String userDBFilePath) {
        dbFile = new File(userDBFilePath);
        this.usersList = new ArrayList<>();
    }
    public void addUser(User u) {
        usersList.add(u);
    }

    public List<User> readUsers() {
        return usersList;
    }

    public void updateUser(User user) {
    }

    public void saveUsers() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dbFile))) {

            for (User u : usersList) {
                bw.write(u.getName() + "\n" + u.getEmail() + "\n" + u.getPayBehavior().toString() + "\n");
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadUsers() {
        List<User> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dbFile))) {

            String line;
            while((line = br.readLine()) != null) {
                String name = line;
                String email = br.readLine();
                String payBehavior = br.readLine();
                User n = new User(
                        name, email, payBehavior.equals("Premium") ? new PremiumPayBehavior() : new StandardPayBehavior());
                list.add(n);
                br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        usersList = new ArrayList<>(list);

    }

}
