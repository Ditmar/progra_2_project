package bussines;

import bussines.interfaces.HandleUserManagement;
import bussines.model.Credential;
import services.UserService;
import ui.UserManagementWindow;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class UserManagement implements HandleUserManagement {
    private static final String VALIDATION_ERROR_TITLE = "Error de Validación";
    private static final String VALIDATION_ERROR_MSG = "Por favor, complete todos los campos.";
    private static final String USER_EXISTS_TITLE = "Advertencia";
    private static final String USER_EXISTS_MSG = "Usuario ya existe. Se guardará de todos modos.";
    private static final String SUCCESS_TITLE = "Éxito";
    private static final String SUCCESS_MSG = "Usuario guardado correctamente.";

    private UserManagementWindow window;
    private final UserService userService;

    public UserManagement() {
        userService = new UserService();
        SwingUtilities.invokeLater(this::initializeUI);
    }

    private void initializeUI() {
        window = new UserManagementWindow();
        window.setOnSaveCallback(this);
        refreshUserList();
        window.setVisible(true);
    }

    @Override
    public void onSaveUser(Credential userData) {
        Objects.requireNonNull(userData, "Datos de usuario no pueden ser nulos");
        
        String username = userData.getUserName().trim();
        String password = userData.getPassword();

        if (username.isEmpty() || password.isEmpty()) {
            window.showMessage(VALIDATION_ERROR_TITLE, VALIDATION_ERROR_MSG, JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (userService.userExists(username)) {
            window.showMessage(USER_EXISTS_TITLE, USER_EXISTS_MSG, JOptionPane.WARNING_MESSAGE);
        }

        userService.addUser(new Credential(username, password));
        refreshUserList();
        window.clearForm();
        window.showMessage(SUCCESS_TITLE, SUCCESS_MSG, JOptionPane.INFORMATION_MESSAGE);
    }

    private void refreshUserList() {
        window.updateTable(userService.getAllUsers());
    }

    @Override
    public void onDeleteUser(String userId) {
        throw new UnsupportedOperationException("Método no implementado aún");
    }
}