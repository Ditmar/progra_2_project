package bussines;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import bussines.model.Credential;
import services.LoginServices;
import services.UserService;
import ui.DashBoard;
import ui.LoginWindow;

public class LoginManagement {
    private LoginWindow mainWindow;
    private LoginServices loginServices;
    private UserService userService;

    public LoginManagement() {
        initServices();
        SwingUtilities.invokeLater(() -> {
            mainWindow = new LoginWindow("Tavo Login");
            mainWindow.setOnLoginCallBack((credential) -> {
                validateFlowCredentials(credential);
            });
        });
    }
    private void initServices() {
        loginServices = new LoginServices();
        userService = new UserService();
    }

    private void validateFlowCredentials(Credential credential) {
        Boolean isValid = loginServices.isValidLogin(credential.getUserName(), credential.getPassword());
        if (isValid) {
            mainWindow.setVisible(false);
            new DashBoard("DashBoard", mainWindow, userService);
        } else {
            JOptionPane.showMessageDialog(mainWindow, "Las credenciasles no son correctas", "Login Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    
}
