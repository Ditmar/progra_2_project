package bussines;

import bussines.model.Credential;
import services.UserService;
import ui.UserManagementWindow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserManagement {

    private final UserService userService;
    private final UserManagementWindow window;

    public UserManagement() {
        userService = new UserService();
        window = new UserManagementWindow();

        window.setGuardarUsuarioListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = window.getUsername();
                String password = window.getPassword();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(window, "Por favor, completa todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Credential cred = new Credential(username, password);
                userService.addUser(cred);
                actualizarTabla();
                window.limpiarFormulario();
                JOptionPane.showMessageDialog(window, "Usuario guardado correctamente.");
            }
        });

        window.setVisible(true);
    }

    private void actualizarTabla() {
        DefaultTableModel model = window.getTableModel();
        model.setRowCount(0);
        for (Credential cred : userService.getAllUsers()) {
            model.addRow(new Object[]{cred.getUserName(), cred.getPassword()});
        }
    }
}
