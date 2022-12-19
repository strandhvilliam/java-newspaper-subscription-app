package Models;

import java.util.List;

public interface UserDAO {

    void createUser(String name, String email, PayBehavior payBehavior);
    List<User> readUsers();
    void updateUser(User user);


}
