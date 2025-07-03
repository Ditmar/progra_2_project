package ui;

import bussines.model.Credential;
import services.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserManagementWindow extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton saveButton;
    private final JButton clearButton;
    private final JTable userTable;
    private final DefaultTableModel tableModel;

    public UserManagementWindow() {
        setTitle("Gestión de Usuarios");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel formulario
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Nuevo Usuario"));

        formPanel.add(new JLabel("Usuario:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Contraseña:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        saveButton = new JButton("Guardar usuario");
        clearButton = new JButton("Limpiar formulario");
        formPanel.add(saveButton);
        formPanel.add(clearButton);

        add(formPanel, BorderLayout.NORTH);

        // Tabla
        tableModel = new DefaultTableModel(new String[] { "Usuario" }, 0);
        userTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(userTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Usuarios registrados"));
        add(tableScroll, BorderLayout.CENTER);
    }

    public String getUsername() {
        return usernameField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void clearForm() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public void updateUserTable(List<Credential> users) {
        tableModel.setRowCount(0);
        for (Credential c : users) {
            tableModel.addRow(new Object[] { c.getUserName() });
        }
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }
}