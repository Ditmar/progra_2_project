package ui;

import bussines.UserManagement;
import bussines.model.Credential;
import ui.components.Button; // Importación explícita
import ui.components.Label;  // Importación explícita
import ui.components.PasswordField; // Importación explícita
import ui.components.TextField; // Importación explícita
import ui.components.SimpleTablePanel;
import ui.config.ColorConfig;
import ui.config.Pointer;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserManagementWindow extends JFrame {
    private final SimpleTablePanel tablePanel;
    private final ui.components.TextField usernameField; // Referencia completa
    private final ui.components.PasswordField passwordField; // Referencia completa
    private final UserManagement userManagement;

    public UserManagementWindow(UserManagement userManagement) {
        super("Gestión de Usuarios");
        this.userManagement = userManagement;
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Crear tabla con columnas
        String[] columns = {"Username", "Password"};
        tablePanel = new SimpleTablePanel(columns, new Object[][]{});
        JScrollPane tableScroll = new JScrollPane(tablePanel.getTable());
        add(tableScroll, BorderLayout.CENTER);

        // Panel de formulario (Sur)
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(ColorConfig.secondary);

        // Usa referencias completas
        usernameField = new ui.components.TextField("Username");
        passwordField = new ui.components.PasswordField("Password");
        ui.components.Button btnSave = new ui.components.Button("Guardar Usuario");
        ui.components.Button btnClear = new ui.components.Button("Limpiar");

        // Estilos y eventos
        btnSave.setCursor(Pointer.HAND_CURSOR);
        btnClear.setCursor(Pointer.HAND_CURSOR);
        
        btnSave.addActionListener(e -> saveUser());
        btnClear.addActionListener(e -> clearForm());

        formPanel.add(new ui.components.Label("Usuario:"));
        formPanel.add(usernameField);
        formPanel.add(new ui.components.Label("Contraseña:"));
        formPanel.add(passwordField);
        formPanel.add(btnSave);
        formPanel.add(btnClear);

        add(formPanel, BorderLayout.SOUTH);
        loadUsers();
        setVisible(true);
    }

    private void loadUsers() {
        List<Credential> users = userManagement.loadUsers();
        for (Credential user : users) {
            tablePanel.addRow(new Object[]{user.getUserName(), "••••"});
        }
    }

  private void saveUser() {
    String username = usernameField.getText().trim();
    String password = new String(passwordField.getPassword()).trim();

    if (username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // PRIMERO VERIFICAR SI EL USUARIO EXISTE ANTES DE AGREGARLO
    if (userManagement.userExists(username)) {
        JOptionPane.showMessageDialog(this, "Usuario ya existe", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return; // Salir sin agregar
    }

    // Si no existe, agregar el nuevo usuario
    Credential newUser = new Credential(username, password);
    userManagement.addUser(newUser);
    tablePanel.addRow(new Object[]{username, "••••"});
    
    // Mensaje de éxito
    JOptionPane.showMessageDialog(this, "Usuario creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
}

    private void clearForm() {
        usernameField.setText("");
        passwordField.setText("");
    }
}