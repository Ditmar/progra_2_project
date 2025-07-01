package bussines;

import javax.swing.SwingUtilities;
import services.UserService;
import ui.UserManagementWindow;

public class UserManagement {
    private UserService userService;
    private UserManagementWindow userWindow;

    public UserManagement(UserService userService ) {
        this.userService = userService;

        SwingUtilities.invokeLater(() -> {
            userWindow = new UserManagementWindow("Gestión de Usuarios", userService);
            userWindow.setOnSaveUserCallback((cred) -> {
                userService.addUser(cred);
                userWindow.updateUserTable(userService.getAllUsers());
            });
            userWindow.updateUserTable(userService.getAllUsers());
        });
    }

    // private void initServices() {
    //     this.userService = new UserService();
    // }
}
