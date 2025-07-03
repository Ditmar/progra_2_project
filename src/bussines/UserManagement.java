package bussines;

import bussines.model.Credential;
import services.UserService;
import ui.UserManagementWindow;

import javax.swing.*;

public class UserManagement {
    private final UserService userService;
    private final UserManagementWindow window;

    public UserManagement() {
        this.userService = new UserService();
        this.window = new UserManagementWindow();

        window.updateUserTable(userService.getAllUsers());

        window.getSaveButton().addActionListener(e -> guardarUsuario());
        window.getClearButton().addActionListener(e -> window.clearForm());

        window.setVisible(true);
    }

    private void guardarUsuario() {
        String username = window.getUsername();
        String password = window.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(window, "Debe llenar todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        userService.addUser(new Credential(username, password));
        window.updateUserTable(userService.getAllUsers());
        JOptionPane.showMessageDialog(window, "Usuario agregado correctamente.");
        window.clearForm();
    }

}