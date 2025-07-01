package ui;

import bussines.UserManagement;
import javax.swing.*;
import java.awt.*;

public class DashBoard extends JFrame {
    public DashBoard() {
        setTitle("Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel sidePanel = new JPanel(new GridLayout(0, 1));
        JButton btnUserManagement = new JButton("Gestión de usuarios");

        btnUserManagement.addActionListener(e -> {
            UserManagement userManagement = new UserManagement();
            userManagement.show();
        });

        sidePanel.add(btnUserManagement);
        add(sidePanel, BorderLayout.WEST);

        JLabel label = new JLabel("Bienvenido al Dashboard");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new DashBoard().setVisible(true);
    }
}