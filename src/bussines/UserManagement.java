package bussines;

import services.UserService;
import models.Credential;
import ui.UserManagementWindow;

public class UserManagement {

    private UserService userService;
    private UserManagementWindow window;

    public UserManagement() {
        userService = new UserService();

        // Instanciar la ventana
        window = new UserManagementWindow();

        // Definir el callback para guardar usuarios
        window.setOnUserSave((username, password) -> {
            Credential newUser = new Credential(username, password);
            userService.addUser(newUser);
            window.updateUserTable(userService.getAllUsers());
        });

        window.setVisible(true);
    }

    public static void main(String[] args) {
        new UserManagement();
    }
}


// Interfaz funcional
public interface UserSaveCallback {
    void onSave(String username, String password);
}

private UserSaveCallback onUserSave;

public void setOnUserSave(UserSaveCallback callback) {
    this.onUserSave = callback;
}