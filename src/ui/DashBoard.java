package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import ui.components.Button;
import ui.components.List;
import ui.components.Panel;
import ui.components.SimpleTablePanel;
import ui.config.Config;

public class DashBoard extends JFrame {
    private String title;
    private Dimension dimension;
    private Panel westPanel, centerPanel;

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
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initUi() {
        createContainer();
        addUserManagementButton(); // Nueva función añadida
        createList();
        createTable();
    }

    private void createContainer() {
        this.westPanel = new Panel();
        this.westPanel.setLayout(new BorderLayout());
        this.westPanel.setSize(200, 300);

        this.centerPanel = new Panel();
        this.centerPanel.setLayout(new BorderLayout());
        this.centerPanel.setSize(300, 500);
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
    }

    // NUEVO MÉTODO AÑADIDO PARA EL BOTÓN DE GESTIÓN
    private void addUserManagementButton() {
        // Crear contenedor para el botón
        Panel buttonContainer = new Panel();
        buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        
        // Crear botón de gestión de usuarios
        Button userManagementButton = new Button("Gestión de usuarios");
        userManagementButton.setPreferredSize(new Dimension(160, 40));
        userManagementButton.addActionListener(e -> {
            // Abrir ventana de gestión al hacer clic
            new bussines.UserManagement(); 
        });
        
        buttonContainer.add(userManagementButton);
        westPanel.add(buttonContainer, BorderLayout.NORTH);
    }

    private void createList() {
        List<String> list = new List<String>();
        list.addItem("RENE");
        list.addItem("LUIS");
        list.addItem("RUBY");
        list.addItem("MAYKOL");
        westPanel.add(list, BorderLayout.CENTER);
    }

    private void createTable() {
        String[] columns = { "ID", "PARCIAL 1 ", "PARCIAL 2", "PARCIAL 3", "Final", "Laboratorio", "Promedio final" };
        Object[][] data = {
            { "1", "8.5", "9.0", "7.5", "8.0", "9.5" },
            { "2", "7.0", "6.5", "8.0", "7.5", "8.0" },
            { "3", "9.0", "8.5", "9.5", "10.0", "9.0" },
            { "4", "6.0", "7.0", "6.5", "7.5", "6.0" },
            { "5", "8.0", "8.5", "9.0", "8.5", "9.0" }
        };
        SimpleTablePanel tablePanel = new SimpleTablePanel(columns, data);
        tablePanel.setVisible(true);
        centerPanel.add(tablePanel, BorderLayout.CENTER);
    }
}