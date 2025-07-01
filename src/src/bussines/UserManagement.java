package bussines;

import services.UserService;
import ui.UserManagementWindow;

public class UserManagement {
    private final UserService userService;
    private final UserManagementWindow window;

    public UserManagement() {
        userService = new UserService();
        window = new UserManagementWindow(userService);
    }

    public void show() {
        window.setVisible(true);
    }
}