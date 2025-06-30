package bussines;

import services.UserService;
import bussines.model.Credential;
import java.util.List;

public class UserManagement {
    private final UserService userService = new UserService();

    public void addUser(Credential user) {
        userService.addUser(user);
    }

    public List<Credential> loadUsers() {
        return userService.getAllUsers();
    }

    public boolean userExists(String username) {
        return userService.userExists(username);
    }
}