package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.components.TextField;
import ui.components.PasswordField;
import ui.components.Button;
import ui.components.SimpleTablePanel;
import ui.OnUserSavedListener;

import bussines.model.Credential;
import services.UserService;

interface OnUserSavedListener {
    void onUserSaved(String username, String password);
}

public class UserManagementWindow extends JFrame {

    private TextField txtUsername;
    private PasswordField txtPassword;
    private Button btnSave;
    private Button btnClear;
    private SimpleTablePanel userTablePanel;
    private OnUserSavedListener listener;

    private UserService userService; // ← nuevo

    public UserManagementWindow() {
        setTitle("Gestión de Usuarios");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        userService = new UserService(); // ← inicializamos

        initComponents();
    }

    private void initComponents() {
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        txtUsername = new TextField("Ingrese nombre de usuario");
        txtPassword = new PasswordField("Ingrese contraseña");
        btnSave = new Button("Guardar usuario");
        btnClear = new Button("Limpiar formulario");

        formPanel.add(new JLabel("Nombre de usuario:"));
        formPanel.add(txtUsername);
        formPanel.add(new JLabel("Contraseña:"));
        formPanel.add(txtPassword);
        formPanel.add(btnSave);
        formPanel.add(btnClear);

        String[] columnas = {"Usuario", "Contraseña"};
        Object[][] datosIniciales = new Object[0][2];
        userTablePanel = new SimpleTablePanel(columnas, datosIniciales);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarUsuario();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
            }
        });

        getContentPane().setLayout(new BorderLayout(10, 10));
        getContentPane().add(formPanel, BorderLayout.NORTH);
        getContentPane().add(userTablePanel, BorderLayout.CENTER);
    }

    private void guardarUsuario() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty() ||
            username.equals("Ingrese nombre de usuario") || password.equals("Ingrese contraseña")) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (listener != null) {
            listener.onUserSaved(username, password);
        } 

        limpiarFormulario();


        Credential nuevoUsuario = new Credential(username, password);
        userService.addUser(nuevoUsuario);

        userTablePanel.addRow(new Object[]{username, password});

        JOptionPane.showMessageDialog(this, "Usuario guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarFormulario();
    }

    public void setOnUserSavedListener(OnUserSavedListener listener) {
    this.listener = listener;
    }

    private void limpiarFormulario() {
        txtUsername.setText("Ingrese nombre de usuario");
        txtPassword.setText("Ingrese contraseña");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserManagementWindow().setVisible(true));
    }

    public SimpleTablePanel getUserTablePanel() {
    return userTablePanel;
    }
}
