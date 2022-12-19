package Models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUserDAO implements UserDAO {
    private final File dbFile;

    private final List<User> usersList;

    public FileUserDAO(String userDBFilePath) {
        dbFile = new File(userDBFilePath);
        this.usersList = new ArrayList<>();
    }

    public void createUser(String name, String email, PayBehavior payBehavior) {
        User u = new User(name, email, payBehavior);
        usersList.add(u);
    }

    public List<User> readUsers() {
        return usersList;
    }

    public void updateUser(User user) {
        /*List<User> uList = deserializeUser();
        for (int i = 0; i < uList.size(); i++) {
            if (uList.get(i).getEmail().equals(user.getEmail())) {
                uList.set(i, user);
            }
        }
        serializeUser(uList);*/
    }

    private void serializeUser(List<User> list) {

        /*try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dbFile))) {
            oos.writeObject(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    private List<User> deserializeUser() {
        List<User> list;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dbFile))) {
            list = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


}
