package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import ui.components.*;
import bussines.model.Credential;

public class UserManagementWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton saveButton;
    private JButton clearButton;
    private SimpleTablePanel tablePanel;
    private JPanel formPanel;
    
    public UserManagementWindow() {
        initComponents();
        setupLayout();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Usuarios");
        setSize(800, 600);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        saveButton = new JButton("Guardar Usuario");
        clearButton = new JButton("Limpiar Formulario");
        
        String[] columnNames = {"Usuario", "Contraseña"};
        Object[][] data = {};
        tablePanel = new SimpleTablePanel(data, columnNames);
        
        clearButton.addActionListener(e -> clearForm());
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        formPanel = createFormPanel();
        add(formPanel, BorderLayout.NORTH);
        
        add(new JScrollPane(tablePanel), BorderLayout.CENTER);
    }
    
    private JPanel createFormPanel() {
        JPanel panel = new Panel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Agregar Usuario"));
        
        JPanel fieldsPanel = new JPanel(new FlowLayout());
        fieldsPanel.add(new JLabel("Usuario:"));
        fieldsPanel.add(usernameField);
        fieldsPanel.add(new JLabel("Contraseña:"));
        fieldsPanel.add(passwordField);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);
        
        panel.add(fieldsPanel);
        panel.add(buttonPanel);
        
        return panel;
    }
    
    public void setSaveButtonListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }
    
    public String getUsername() {
        return usernameField.getText().trim();
    }
    
    public String getPassword() {
        return new String(passwordField.getPassword());
    }
    
    private void clearForm() {
        usernameField.setText("");
        passwordField.setText("");
    }
    
    public void updateTable(Object[][] data) {
        tablePanel.updateData(data);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
