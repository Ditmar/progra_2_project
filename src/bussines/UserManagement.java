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
