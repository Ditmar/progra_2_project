package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import bussines.interfaces.HandleUserManagement;
import bussines.model.Credential;
import ui.components.*;
import ui.components.Button;
import ui.components.TextField;

public class UserManagementWindow extends JFrame {
    private SimpleTablePanel tablePanel;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button saveButton;
    private Button clearButton;
    private HandleUserManagement callback;

    public UserManagementWindow() {
        super("Gestión de Usuarios");
        configureWindow();
        initComponents();
        setupLayout();
    }

    private void configureWindow() {
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(mainPanel);

        tablePanel = new SimpleTablePanel(new String[]{"Nombre de Usuario"}, new Object[0][1]);
        tablePanel.setBorder(BorderFactory.createTitledBorder("Usuarios Registrados"));
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        JPanel formPanel = createFormPanel();
        mainPanel.add(formPanel, BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Agregar Nuevo Usuario"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos de texto
        usernameField = new TextField("Ingrese el nombre de usuario");
        passwordField = new PasswordField("Ingrese la contraseña");

        // Botones
        saveButton = new Button("Guardar Usuario");
        clearButton = new Button("Limpiar Formulario");

        // Posicionamiento
        addFormField(formPanel, gbc, new JLabel("Usuario:"), 0, 0);
        addFormField(formPanel, gbc, usernameField, 1, 0);
        addFormField(formPanel, gbc, new JLabel("Contraseña:"), 0, 1);
        addFormField(formPanel, gbc, passwordField, 1, 1);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        addFormField(formPanel, gbc, buttonPanel, 1, 2);

        return formPanel;
    }

    private void addFormField(JPanel panel, GridBagConstraints gbc, Component component, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = (x == 1) ? 1.0 : 0;
        panel.add(component, gbc);
    }

    // Resto de métodos (sin cambios en funcionalidad)
    public void setOnSaveCallback(HandleUserManagement callback) {
        this.callback = callback;
    }
    
    public void clearForm() {
        usernameField.setText("");
        passwordField.setText("");
    }
    
    public void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
    
    public void updateTable(List<Credential> userList) {
        DefaultTableModel model = tablePanel.getModel();
        model.setRowCount(0);
        for (Credential user : userList) {
            model.addRow(new Object[]{user.getUserName()});
        }
    }
}