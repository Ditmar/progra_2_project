package bussines;
import javax.swing.JOptionPane;

import bussines.model.Credential;
import services.UserService;
import ui.UserManagementWindow;

public class UserManagement {
    private final UserService userService = new UserService();
    private final UserManagementWindow window;

    public UserManagement() {
        window = new UserManagementWindow("Gestión de usuarios");

        /* — Callback del botón Guardar — */
        window.setSaveCallback(cred -> {
            userService.addUser(cred);
            window.refreshTable(userService.getAllUsers());
            JOptionPane.showMessageDialog(window,
                    "Usuario guardado (se permiten duplicados)",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }
} 

