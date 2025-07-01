package ui;

import services.UserService;
import javax.swing.*;
import bussines.model.Credential;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTablemodel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserManagementWindow extends JFrame {
    private Userservice userService;
    private JTextField usernamField;
    private JPasswordField passwordField;
    private DefaultTablemodel tableModel;
    private JTable userTable;
    public UserManagementWindow(){
        userService = new UserService();
        setTitle("Gestor de Usuarios");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();

    }
    private void iniComponents(){
        JPanel = new JPanel(new BorderLayout(10,10));
        getContentPane().add(mainPanel);
        JPanel formPanel= new JPanel(new GridLayout(3,2,5,5));
        formPanel.setBorder(BorderFactory.createTitledBorder("nuevo usuario"));
        formPanel.add(new JLabel("usuario"));
        usernameField =new JTextField();
        formPanel.add(usernameField);
        formPanel.add(new JLabel("contrseña"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        JButton saveButton = new JButton("Guardar usuario");
        saveButton.addActionListener(this::saveUser);
        formPanel.add(saveButton);

        JButton clearButton = new JButton("Limpiar formulario");
        clearButton.addActionListener(e -> clearForm());
        formPanel.add(clearButton);

        mainPanel.add(formPanel, BorderLayout.NORTH);

        // Panel inferior - Tabla
        tableModel = new DefaultTableModel(new String[]{"Usuario", "Contraseña"}, 0);
        userTable = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(userTable);

        mainPanel.add(tableScroll, BorderLayout.CENTER);
    }

    private void saveUser(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Credential newUser = new Credential(username, password);
        userService.addUser(newUser);
        updateUserTable();
        clearForm();
        JOptionPane.showMessageDialog(this, "Usuario guardado correctamente");
    }

    private void clearForm() {
        usernameField.setText("");
        passwordField.setText("");
    }

    private void updateUserTable() {
        tableModel.setRowCount(0); // Limpiar tabla
        for (Credential c : userService.getAllUsers()) {
            tableModel.addRow(new Object[]{c.getUsername(), c.getPassword()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserManagementWindow().setVisible(true));
    }
}
