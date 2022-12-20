package Models;

import java.util.List;

public interface UserDAO {

    void addUser(User user);
    List<User> readUsers();
    void updateUser(User user);

    void saveUsers();
    void loadUsers();


}
