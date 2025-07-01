package bussines;

import services.UserService;
import ui.UserManagementWindow;
import bussines.model.Credential;
import javax.swing.JOptionPane;

public class UserManagement {
    private UserManagementWindow window;
    private UserService userService;

    public UserManagement() {
        userService = new UserService();
        window = new UserManagementWindow();
        
        // Configurar callback para guardar usuario
        window.setOnSaveUserCallback(credential -> {
            if (credential.getUserName().isEmpty() || credential.getPassword().isEmpty()) {
                JOptionPane.showMessageDialog(window, "Campos vacíos no permitidos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            userService.addUser(credential);
            window.updateUserTable(userService.getAllUsers());
            window.clearForm();
            JOptionPane.showMessageDialog(window, "Usuario guardado exitosamente");
        });
        
        window.setVisible(true);
    }
}