package ui;

import bussines.interfaces.HandleUserManagement;
import bussines.model.Credential;
import ui.components.Button;
import ui.components.SimpleTablePanel;
import ui.components.TextField;
import ui.components.PasswordField;
import ui.components.Panel;
import ui.components.Label;
import ui.config.Config;
import ui.config.ColorConfig;
import ui.config.Typography;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.util.List;

public class UserManagementWindow extends JFrame {
    private HandleUserManagement handler;
    private SimpleTablePanel table;
    private TextField userField;
    private PasswordField passField;
    private Button saveBtn;
    private Button clearBtn;

    public UserManagementWindow(String title) {
        super(title);
        setSize(Config.WIDTH, Config.HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        initUi();
        setVisible(true);
    }

    private void initUi() {

        Panel leftPanel = new Panel();
        leftPanel.setBackground(ColorConfig.primary);
        leftPanel.setLayout(new java.awt.BorderLayout());
        leftPanel.setPreferredSize(new Dimension(Config.WIDTH/2, Config.HEIGHT));

        String[] cols = {"Usuario"};
        Object[][] data = {};
        table = new SimpleTablePanel(cols, data);
        leftPanel.add(table, java.awt.BorderLayout.CENTER);

        Panel rightPanel = new Panel();
        rightPanel.setBackground(ColorConfig.secondary);
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(Config.WIDTH/2, Config.HEIGHT));

        Label lbl = new Label("gestion de usuarios");
        lbl.setFont(Typography.MontSerratBoldDisplay1);
        lbl.setAlignmentX(CENTER_ALIGNMENT);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(lbl);

        userField = new TextField("Usuario");
        userField.setMaximumSize(new Dimension(300, 30));
        userField.setAlignmentX(CENTER_ALIGNMENT);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(userField);

        passField = new PasswordField("Contraseña");
        passField.setMaximumSize(new Dimension(300, 30));
        passField.setAlignmentX(CENTER_ALIGNMENT);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(passField);

        saveBtn = new Button("Guardar usuario");
        saveBtn.setAlignmentX(CENTER_ALIGNMENT);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(saveBtn);

        clearBtn = new Button("Limpiar formulario");
        clearBtn.setAlignmentX(CENTER_ALIGNMENT);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(clearBtn);

        add(leftPanel);
        add(rightPanel);

        setActions();
    }

    private void setActions() {
        saveBtn.addActionListener(e -> {
            String u = userField.getText().trim();
            String p = passField.getText().trim();
            if (u.isEmpty() || p.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete ambos campos.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            table.addRow(new Object[]{u});
            handler.onSaveUser(new Credential(u, p));
        });

        clearBtn.addActionListener(e -> {
            userField.setText("");
            passField.setText("");
        });
    }

    public void setOnSaveUserCallback(HandleUserManagement handler) {
        this.handler = handler;
    }

    public void refresh(List<Credential> users) {
        table.getModel().setRowCount(0);
        for (Credential c : users) {
            table.addRow(new Object[]{c.getUserName()});
        }
        if (users.size() > 1) {
            JOptionPane.showMessageDialog(this,
                "Se han agregado usuarios con nombres repetidos.",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}