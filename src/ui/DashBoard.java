package ui;

import java.awt.*;
import javax.swing.*;
import bussines.UserManagement;
import ui.components.*;
import ui.components.Button;
import ui.components.Panel;
import ui.config.Config;

public class DashBoard extends JFrame {
    private static final String TITLE = "DashBoard Principal";
    private static final String USER_MGMT_BTN_TEXT = "Gestión de Usuarios";
    private static final String[] TABLE_COLUMNS = {"ID", "Parcial 1", "Parcial 2", "Parcial 3", "Final", "Laboratorio"};
    private static final Object[][] TABLE_DATA = {
        {"1", "8.5", "9.0", "7.5", "8.0", "9.5"},
        {"2", "7.0", "6.5", "8.0", "7.5", "8.0"},
        {"3", "9.0", "8.5", "9.5", "10.0", "9.0"},
        {"4", "6.0", "7.0", "6.5", "7.5", "6.0"},
        {"5", "8.0", "8.5", "9.0", "8.5", "9.0"}
    };

    private Panel westPanel;
    private Panel centerPanel;

    public DashBoard() {
        super(TITLE);
        configureWindow();
        initPanels();
        populateWestPanel();
        populateCenterPanel();
    }

    private void configureWindow() {
        setSize(Config.WIDTH, Config.HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initPanels() {
        westPanel = new Panel();
        westPanel.setLayout(new BorderLayout());
        westPanel.setPreferredSize(new Dimension(200, 0));

        centerPanel = new Panel();
        centerPanel.setLayout(new BorderLayout());

        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void populateWestPanel() {
        List<String> itemList = createListItems();
        westPanel.add(new List<>(itemList), BorderLayout.CENTER);
        westPanel.add(createUserMgmtButton(), BorderLayout.SOUTH);
    }

    private List<String> createListItems() {
        return List.of("Maria", "Juan", "Lucas", "John");
    }

    private Button createUserMgmtButton() {
        Button button = new Button(USER_MGMT_BTN_TEXT);
        button.addActionListener(e -> new UserManagement());
        return button;
    }

    private void populateCenterPanel() {
        SimpleTablePanel tablePanel = new SimpleTablePanel(TABLE_COLUMNS, TABLE_DATA);
        tablePanel.setSize(600, 300);
        centerPanel.add(tablePanel, BorderLayout.CENTER);
    }
}