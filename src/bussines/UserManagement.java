package bussines;

import bussines.model.Credential;
import services.UserService;
import ui.UserManagementWindow;

public class UserManagement {

    private UserService userService;
    private UserManagementWindow userWindow;

    public UserManagement() {
        userService = new UserService();
        userWindow = new UserManagementWindow();
        userWindow.setVisible(true);
    }

    public static void main(String[] args) {
        new UserManagement();
    }
}