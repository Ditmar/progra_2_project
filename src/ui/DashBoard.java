package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

import bussines.UserManagement;
import ui.config.Config;
import ui.components.Button;
import ui.components.List;
import ui.components.Panel;
import ui.components.SimpleTablePanel;

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
        createList();
        createTable();
    }

    private void createContainer() {
        westPanel = new Panel();
        westPanel.setLayout(new BorderLayout());
        westPanel.setPreferredSize(new Dimension(200, Config.HEIGHT));

        centerPanel = new Panel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setPreferredSize(new Dimension(Config.WIDTH - 200, Config.HEIGHT));

        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void createList() {
        List<String> list = new List<>();
        list.addItem("Maria");
        list.addItem("Juan");
        list.addItem("Lucas");
        list.addItem("John");
        westPanel.add(list, BorderLayout.CENTER);

        Button userMgmtBtn = new Button("Gestión de usuarios");
        userMgmtBtn.setPreferredSize(new Dimension(180, 40));
        userMgmtBtn.addActionListener(e -> new UserManagement());
        westPanel.add(userMgmtBtn, BorderLayout.SOUTH);
    }

    private void createTable() {
        String[] columns = { "ID", "Parcial 1", "Parcial 2", "Parcial 3", "Final", "Laboratorio", "Promedio final" };
        Object[][] data = {
            { "1", "8.5", "9.0", "7.5", "8.0", "9.5", "8.7" },
            { "2", "7.0", "6.5", "8.0", "7.5", "8.0", "7.4" },
            { "3", "9.0", "8.5", "9.5", "10.0", "9.0", "9.2" },
            { "4", "6.0", "7.0", "6.5", "7.5", "6.0", "6.6" },
            { "5", "8.0", "8.5", "9.0", "8.5", "9.0", "8.6" }
        };
        SimpleTablePanel tablePanel = new SimpleTablePanel(columns, data);
        tablePanel.setSize(600, 300);
        tablePanel.setVisible(true);
        centerPanel.add(tablePanel, BorderLayout.CENTER);
    }
}
