package ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

import ui.components.Panel;
import ui.components.SimpleTablePanel;
import services.UserService;
import bussines.model.Credential;


public class UserManagementWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private SimpleTablePanel tablePanel;
    private UserService userService;
    private Consumer<Credential> onSaveCallback;

    public UserManagementWindow(String title, UserService userService) {
        super(title);
        this.userService = userService;
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        initUI();
        setVisible(true);
    }

    private void initUI() {
        Panel formPanel = new Panel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 10));
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        JButton saveButton = new JButton("Guardar usuario");
        JButton clearButton = new JButton("Limpiar formulario");

        saveButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
                
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Por favor, complete ambos campos: usuario y contraseña.",
                    "Campos requeridos",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            boolean usuarioYaExiste = userService.getAllUsers().stream()
                .anyMatch(u -> u.getUserName().equalsIgnoreCase(username));

            if (usuarioYaExiste) {
                JOptionPane.showMessageDialog(
                    this,
                    "Advertencia: El usuario ya existe, pero se añadirá igualmente.",
                    "Usuario duplicado",
                    JOptionPane.WARNING_MESSAGE
                );
            }

            Credential cred = new Credential(username, password);
            if (onSaveCallback != null) {
                onSaveCallback.accept(cred);
            }
            clearForm();
        
        });

        clearButton.addActionListener(e -> clearForm());

        formPanel.add(new JLabel("Usuario:"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Contraseña:"));
        formPanel.add(passwordField);
        formPanel.add(saveButton);
        formPanel.add(clearButton);

        add(formPanel, BorderLayout.NORTH);
        tablePanel = new SimpleTablePanel(new String[]{"Usuario"}, new Object[0][0]);
        add(tablePanel, BorderLayout.CENTER);
        updateUserTable(userService.getAllUsers());
        // updateTable();
    }

    private void clearForm() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public void setOnSaveUserCallback(Consumer<Credential> callback) {
        this.onSaveCallback = callback;
    }

    public void updateUserTable(List<Credential> users) {
        if (users == null) {
            // System.out.println("❌ La lista de usuarios llegó como null");
            users = List.of();
        } else {
            // System.out.println("✅ Lista recibida con " + users.size() + " usuarios:");
            users.forEach(u -> System.out.println(" - " + u.getUserName()));
        }

        Object[][] data = users.stream()
            .map(u -> new Object[]{ u.getUserName() })
            .toArray(Object[][]::new);
        tablePanel.setData(data);
        tablePanel.revalidate();
        tablePanel.repaint();
    }
}
