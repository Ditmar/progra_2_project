package business;

import services.UserService;
import ui.UserManagementWindow;
import model.Credential;
import java.util.List;

public class UserManagement {
    private UserService userService;
    private UserManagementWindow window;
    
    public UserManagement() {
        this.userService = new UserService();
        this.window = new UserManagementWindow();
        setupEventHandlers();
        refreshTable();
    }
    
    private void setupEventHandlers() {
        window.setSaveButtonListener(e -> saveUser());
    }
    
    private void saveUser() {
        try {
            String username = window.getUsername();
            String password = window.getPassword();
            
            if (username.isEmpty() || password.isEmpty()) {
                window.showMessage("⚠️ Por favor, complete todos los campos");
                return;
            }
            
            if (userService.userExists(username)) {
                window.showMessage("⚠️ El usuario '" + username + "' ya existe, pero se agregará de nuevo");
            }
            
            Credential newUser = new Credential(username, password);
            userService.addUser(newUser);
            
            refreshTable();
            window.showMessage("✅ Usuario guardado exitosamente");
            
        } catch (Exception ex) {
            window.showMessage("❌ Error al guardar usuario: " + ex.getMessage());
        }
    }
    
    private void refreshTable() {
        List<Credential> users = userService.getAllUsers();
        Object[][] tableData = new Object[users.size()][2];
        
        for (int i = 0; i < users.size(); i++) {
            Credential user = users.get(i);
            tableData[i][0] = user.getUsername();
            tableData[i][1] = "***";
        }
        
        window.updateTable(tableData);
    }
    
    public void showWindow() {
        window.setVisible(true);
    }
    
    public UserService getUserService() {
        return userService;
    }
}
