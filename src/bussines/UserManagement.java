package bussines;

import bussines.interfaces.HandleUserManagement;
import bussines.model.Credential;
import services.*;
import ui.UserManagementWindow;

import javax.swing.SwingUtilities;

public class UserManagement implements HandleUserManagement {
    private final UserService userService;
    private UserManagementWindow window;

    public UserManagement() {
        userService = new UserService();
        SwingUtilities.invokeLater(this::createAndShowUi);
    }

    private void createAndShowUi() {
        window = new UserManagementWindow("Gestión de Usuarios");
        window.setOnSaveUserCallback(this);
        window.refresh(userService.getAllUsers());
        window.setVisible(true);
    }

    @Override
    public void onSaveUser(Credential cred) {
        userService.addUser(cred);
        window.refresh(userService.getAllUsers());
    }
}