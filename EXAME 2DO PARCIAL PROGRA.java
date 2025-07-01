package bussines;

import bussines.interfaces.HandlerLogin;
import bussines.model.Credential;
import services.LoginServices; // Ya no importaremos esta clase directamente
import services.interfaces.ILoginService; // <--- ¡Importa la nueva interfaz de servicio!
import ui.LoginWindow;

public class LoginManagement implements HandlerLogin {
    // private LoginServices loginService; // <--- ANTES
    private ILoginService loginService; // <--- AHORA: Declara la interfaz
    private LoginWindow loginWindow;

    public LoginManagement(LoginWindow loginWindow) {
        // this.loginService = new LoginServices(); // <--- ANTES
        this.loginService = new LoginServices(); // <--- AHORA: Instancia la implementación concreta
        this.loginWindow = loginWindow;
        // ... (resto del código del constructor)
    }

    @Override
    public void clickLogin(Credential credential) {
        String email = credential.getUsername();
        String password = credential.getPassword();

        if (email.isEmpty() || password.isEmpty()) {
            loginWindow.showMessage("Email and password cannot be empty.", "Login Error", false);
            return;
        }

        // Aquí no hay cambios, ya que el método isValidLogin es el mismo en la interfaz
        if (loginService.isValidLogin(email, password)) {
            loginWindow.showMessage("Login successful!", "Success", true);
            loginWindow.dispose();
            // TODO: Open your Dashboard
            // new DashBoard().setVisible(true);
        } else {
            loginWindow.showMessage("Invalid email or password.", "Login Error", false);
        }
    }
}