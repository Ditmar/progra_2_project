package ui;

import bussines.model.Credential;
import ui.components.Button;
import ui.components.Panel;
import ui.components.PasswordField;
import ui.components.SimpleTablePanel;
import ui.components.TextField;
import ui.config.Config;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.function.Consumer;

public class UserManagementWindow extends JFrame {
    private SimpleTablePanel tablePanel;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button saveButton;
    private Button clearButton;
    private Consumer<Credential> onSaveUserCallback;

    public UserManagementWindow() {
        super("Gestión de Usuarios");
        setSize(new Dimension(600, 400));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Crear tabla de usuarios
        String[] columnNames = {"Usuario", "Contraseña"};
        tablePanel = new SimpleTablePanel(columnNames, new Object[][]{});
        add(new JScrollPane(tablePanel.getTable()), BorderLayout.CENTER);
        
        // Crear formulario
        Panel formPanel = new Panel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));
        
        formPanel.add(new ui.components.Label("Usuario:"));
        usernameField = new TextField("");
        formPanel.add(usernameField);
        
        formPanel.add(new ui.components.Label("Contraseña:"));
        passwordField = new PasswordField("");
        formPanel.add(passwordField);
        
        // Panel de botones
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new FlowLayout());
        
        saveButton = new Button("Guardar");
        saveButton.addActionListener(e -> saveUser());
        buttonPanel.add(saveButton);
        
        clearButton = new Button("Limpiar");
        clearButton.addActionListener(e -> clearForm());
        buttonPanel.add(clearButton);
        
        formPanel.add(buttonPanel);
        add(formPanel, BorderLayout.SOUTH);
    }
    
    private void saveUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        Credential credential = new Credential(username, password);
        
        if(onSaveUserCallback != null) {
            onSaveUserCallback.accept(credential);
        }
    }
    
    public void clearForm() {
        usernameField.setText("");
        passwordField.setText("");
    }
    
    public void updateUserTable(List<Credential> users) {
        DefaultTableModel model = (DefaultTableModel) tablePanel.getModel();
        model.setRowCount(0); // Limpiar tabla
        
        for(Credential user : users) {
            model.addRow(new Object[]{user.getUserName(), user.getPassword()});
        }
    }
    
    public void setOnSaveUserCallback(Consumer<Credential> callback) {
        this.onSaveUserCallback = callback;
    }
}