package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import bussines.UserManagement;
import ui.components.Button;
import ui.components.List;
import ui.components.Panel;
import ui.components.SimpleTablePanel;
import ui.config.Config;
import ui.config.Pointer;

public class DashBoard extends JFrame {
    private String title;
    private Dimension dimension;
    private Panel westPanel;
    private Panel centerPanel;

    public DashBoard(String title) {
        super(title);
        this.title = title;
        this.dimension = new Dimension(Config.WIDTH, Config.HEIGHT);
        this.initConfig();
        this.initUi();
    }

    private void initConfig() {
        this.setSize(new Dimension(this.dimension));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initUi() {
        this.createContainer();
        this.createList();
        this.createTable();
    }

    private void createContainer() {
        this.westPanel = new Panel();
        this.westPanel.setLayout(new BorderLayout());
        this.westPanel.setSize(200, 300);
        
        this.centerPanel = new Panel();
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.setSize(300, 500);
        
        this.add(this.westPanel, BorderLayout.WEST);
        this.add(this.centerPanel, BorderLayout.CENTER);
    }

    private void createList() {
        // Panel contenedor para el botón y la lista
        Panel containerPanel = new Panel();
        containerPanel.setLayout(new BorderLayout());
        
        // Botón de gestión de usuarios
        Button userManagementBtn = new Button("Gestión de usuarios");
        userManagementBtn.setCursor(Pointer.HAND_CURSOR);
        userManagementBtn.addActionListener(e -> openUserManagement());
        
        // Lista original
        List<String> list = new List<>();
        list.addItem("Maria");
        list.addItem("Juan");
        list.addItem("Lucas");
        list.addItem("John");
        
        // Agregar componentes al contenedor
        containerPanel.add(userManagementBtn, BorderLayout.NORTH);
        containerPanel.add(new JScrollPane(list.getList()), BorderLayout.CENTER);
        
        this.westPanel.add(containerPanel, BorderLayout.CENTER);
    }

    private void openUserManagement() {
        // Crear instancia de UserManagement y abrir la ventana
        new UserManagementWindow(new UserManagement());
    }

    private void createTable() {
        String[] columns = new String[]{"ID", "Parcial 1 ", "Parcial 2", "Parcial 3", "Final", "Laboratorio", "Promedio final"};
        Object[][] data = new Object[][]{
            {"1", "8.5", "9.0", "7.5", "8.0", "9.5", "8.7"},
            {"2", "7.0", "6.5", "8.0", "7.5", "8.0", "7.4"},
            {"3", "9.0", "8.5", "9.5", "10.0", "9.0", "9.2"},
            {"4", "6.0", "7.0", "6.5", "7.5", "6.0", "6.6"},
            {"5", "8.0", "8.5", "9.0", "8.5", "9.0", "8.6"}
        };
        
        SimpleTablePanel tablePanel = new SimpleTablePanel(columns, data);
        JScrollPane scrollPane = new JScrollPane(tablePanel.getTable());
        scrollPane.setPreferredSize(new Dimension(600, 300));
        
        this.centerPanel.add(scrollPane, BorderLayout.CENTER);
    }
}
