package ui;

import javax.swing.*;
import java.awt.*;
import bussines.UserManagement;
import ui.config.*;;;

public class DashBoard extends JFrame {
    private Panel westPanel, centerPanel;
    private JButton userManagementButton;

    public DashBoard(String title) {
        super(title);
        this.setSize(new Dimension(Config.WIDTH, Config.HEIGHT));
        this.initConfig();
        this.initUI();
    }

    private void initConfig() {
        this.setSize(new Dimension(Config.WIDTH, Config.HEIGHT));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void initUI() {
        createContainer();
        createList();
        createTable();
    }

    private void createContainer() {
        this.westPanel = new Panel();
        this.westPanel.setLayout(new BorderLayout());
        this.westPanel.setSize(200, 300);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        userManagementButton = new JButton("Gestión de usuarios");
        userManagementButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        userManagementButton.addActionListener(e -> openUserManagement());

        buttonPanel.add(Box.createVerticalStrut(20));
        buttonPanel.add(userManagementButton);
        buttonPanel.add(Box.createVerticalGlue());

        this.westPanel.add(buttonPanel, BorderLayout.NORTH);

        this.add(westPanel, BorderLayout.WEST);

        this.centerPanel = new Panel();
        this.add(centerPanel, BorderLayout.CENTER);
    }

    private void openUserManagement() {
        UserManagement userManagement = new UserManagement();
        userManagement.showWindow();
    }

    private void createList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Inicio");
        listModel.addElement("Reportes");
        listModel.addElement("Configuración");

        JList<String> list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(180, 80));

        westPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void createTable() {
        String[] columnNames = {"ID", "Nombre", "Estado"};
        Object[][] data = {
            {"1", "Usuario 1", "Activo"},
            {"2", "Usuario 2", "Inactivo"},
            {"3", "Usuario 3", "Activo"}
        };

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
    }
}
