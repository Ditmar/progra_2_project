package ui;

import bussines.model.Credential;
import services.UserService;
import ui.components.*;
import ui.components.Button;
import ui.components.Label;
import ui.components.Panel;
import ui.components.TextField;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UserManagementWindow extends JFrame {
    private TextField usernameField;
    private PasswordField passwordField;
    private SimpleTablePanel tablePanel;
    private UserService userService;

    public UserManagementWindow(UserService userService) {
        this.userService = userService;
        setTitle("Gestión de Usuarios");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        Panel mainPanel = new Panel(new BorderLayout(10, 10));
        Panel formPanel = new Panel(new GridLayout(3, 2, 5, 5));

        usernameField = new TextField(getName());
        passwordField = new PasswordField(null);

        formPanel.add(new Label("Usuario:"));
        formPanel.add(usernameField);
        formPanel.add(new Label("Contraseña:"));
        formPanel.add(passwordField);

        Button btnSave = new Button("Guardar usuario");
        Button btnClear = new Button("Limpiar formulario");

        btnSave.addActionListener(e -> saveUser());
        btnClear.addActionListener(e -> clearForm());

        Panel buttonPanel = new Panel(new FlowLayout());
        buttonPanel.add(btnSave);
        buttonPanel.add(btnClear);

        tablePanel = new SimpleTablePanel(new String[]{"Usuario"}, null);
        updateUserList();

        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(tablePanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void saveUser() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        for (Credential c : userService.getAllUsers()) {
    System.out.println("Registrado: " + c.getUserName());
}


        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se permiten campos vacíos.");
            return;
        }

        userService.addUser(new Credential(username, password));
        JOptionPane.showMessageDialog(this, "Usuario agregado correctamente (pueden repetirse nombres).");
        updateUserList();
        for (Credential c : userService.getAllUsers()) {
    System.out.println("Registrado: " + c.getUserName());
}

    }

    private void clearForm() {
        usernameField.setText("");
        passwordField.setText("");
    }

    private void updateUserList() {
        List<Credential> users = userService.getAllUsers();
        String[][] data = new String[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i).getUserName();
        }
        tablePanel.setData(data);
    }
}