package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui.UserManagementWindow;
import ui.config.Config;
import ui.components.SimpleTablePanel;

public class DashBoard extends JFrame {
    private String title;
    private Dimension dimension;
    private JPanel westPanel, centerPanel;

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
        createUserManagementButton();
        createList();
        createTable();
    }

    private void createContainer() {
        westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.setPreferredSize(new Dimension(200, 0));

        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void createUserManagementButton() {
        JButton userMgmtBtn = new JButton("Gestión de usuarios");
        userMgmtBtn.setAlignmentX(JButton.CENTER_ALIGNMENT);
        westPanel.add(userMgmtBtn);
        userMgmtBtn.addActionListener(e -> {
            UserManagementWindow userWindow = new UserManagementWindow();
            userWindow.setVisible(true);
        });

        westPanel.revalidate();
        westPanel.repaint();
    }

    private void createList() {
        String[] users = { "Maria", "Juan", "Lucas", "John" };
        JList<String> list = new JList<>(users);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(200, 150));
        westPanel.add(scrollPane);

        westPanel.revalidate();
        westPanel.repaint();
    }

    private void createTable() {
        String[] columns = { "ID", "Parcial 1 ", "Parcial 2", "Parcial 3", "Final", "Laboratorio", "Promedio final" };
        Object[][] data = {
                { "1", "8.5", "9.0", "7.5", "8.0", "9.5" },
                { "2", "7.0", "6.5", "8.0", "7.5", "8.0" },
                { "3", "9.0", "8.5", "9.5", "10.0", "9.0" },
                { "4", "6.0", "7.0", "6.5", "7.5", "6.0" },
                { "5", "8.0", "8.5", "9.0", "8.5", "9.0" }
        };
        SimpleTablePanel tablePanel = new SimpleTablePanel(columns, data);
        tablePanel.setPreferredSize(new Dimension(600, 300));
        centerPanel.add(tablePanel, BorderLayout.CENTER);

        centerPanel.revalidate();
        centerPanel.repaint();
    }
}
