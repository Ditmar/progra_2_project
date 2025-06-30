package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import services.UserService;
import ui.config.Config;
import ui.components.List;
import ui.components.Panel;
import ui.components.SimpleTablePanel;
import bussines.UserManagement;

public class DashBoard extends JFrame {
    private String title;
    private Dimension dimension;
    private Panel westPanel, centerPanel;
    private LoginWindow loginWindow;
    private UserService userService;

    public DashBoard(String title, LoginWindow loginWindow, UserService userService) {
        super(title);
        this.loginWindow = loginWindow;
        this.userService = userService;
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
        createList();
        createTable();
        createBackButton();
        createActionsPanel();
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

    private void createList() {
        List<String> list = new List<String>();
        list.addItem("Maria");
        list.addItem("Juan");
        list.addItem("Lucas");
        list.addItem("John");
        westPanel.add(list, BorderLayout.CENTER);
    }

    private void createTable() {
        // fake data
        String[] columns = { "ID", "Parcial 1 ", "Parcial 2", "Parcial 3", "Final", "Laboratorio", "Promedio final" };
        Object[][] data = {
                { "1", "8.5", "9.0", "7.5", "8.0", "9.5" },
                { "2", "7.0", "6.5", "8.0", "7.5", "8.0" },
                { "3", "9.0", "8.5", "9.5", "10.0", "9.0" },
                { "4", "6.0", "7.0", "6.5", "7.5", "6.0" },
                { "5", "8.0", "8.5", "9.0", "8.5", "9.0" }
        };
        SimpleTablePanel tablePanel = new SimpleTablePanel(columns, data);
        tablePanel.setSize(600, 300);
        tablePanel.setVisible(true);
        centerPanel.add(tablePanel, BorderLayout.CENTER);
    }

    private void createBackButton() {
        JButton backButton = new JButton("Cerrar sesión");
        backButton.setPreferredSize(new Dimension(150, 30));
        backButton.addActionListener((event) -> {
            this.dispose(); // Cierra el DashBoard
            loginWindow.setVisible(true); // Vuelve a mostrar la ventana de login
        });
        westPanel.add(backButton, BorderLayout.SOUTH);
    }
    
    private void createActionsPanel() {
        JButton userMgmtButton = new JButton("Gestión de usuarios");
        userMgmtButton.setPreferredSize(new Dimension(180, 30));
        userMgmtButton.addActionListener(e-> {
            new UserManagement(userService);
        });

        JButton logoutButton = new JButton("Cerrar sesión");
        logoutButton.setPreferredSize(new Dimension(180, 30));
        logoutButton.addActionListener(e -> {
            this.dispose();
            loginWindow.setVisible(true);
        });

        JPanel buttonsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonsPanel.add(userMgmtButton);
        buttonsPanel.add(logoutButton);
        westPanel.add(buttonsPanel, BorderLayout.SOUTH);
    }

}
