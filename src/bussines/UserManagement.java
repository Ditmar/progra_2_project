package bussines;

import bussines.model.Credential;
import services.UserService;
import ui.UserManagementWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserManagement {

    private UserManagementWindow window;
    private UserService userService;

    public UserManagement() {
        this.window = new UserManagementWindow();
        this.userService = new UserService();

        initListeners();

        this.window.setVisible(true);
    }

    private void initListeners() {
        window.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = window.getUsernameField().getText().trim();
                String password = window.getPasswordField().getText().trim();

                if (username.isEmpty() || password.isEmpty() ||
                        username.equals("Nombre de usuario") || password.equals("Contraseña")) {
                    JOptionPane.showMessageDialog(window, "Completa todos los campos.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean exists = userService.getAllUsers().stream()
                        .anyMatch(c -> c.getUserName().equals(username) && c.getPassword().equals(password));

                if (exists) {
                    JOptionPane.showMessageDialog(window, "Este usuario ya existe.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Credential newUser = new Credential(username, password);
                userService.addUser(newUser);
                JOptionPane.showMessageDialog(window, "Usuario guardado: " + username);
                window.getUsernameField().setText("Nombre de usuario");
                window.getPasswordField().setText("Contraseña");
            }
        });

        window.getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.getUsernameField().setText("Nombre de usuario");
                window.getPasswordField().setText("Contraseña");
            }
        });
    }
}
