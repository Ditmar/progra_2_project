package ui; 



import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import bussines.model.Credential;
import ui.components.Button;
import ui.components.Panel;
import ui.components.PasswordField;
import ui.components.SimpleTablePanel;
import ui.components.TextField;
import ui.config.Config;

public class UserManagementWindow extends JFrame {

 private final TextField txtUser     = new TextField("Usuario");
 private final PasswordField txtPassword = new PasswordField("Contraseña");
 private final Button btnSave = new Button("Guardar usuario");
 private final Button btnClear = new Button("Limpiar formulario");
    private SimpleTablePanel tablePanel;

    public interface SaveCallback {
        void onSave(Credential cred);
    }
    private SaveCallback saveCallback;

    public UserManagementWindow(String title) {
        super(title);
        initConfig();
        buildUi();
    }

    private void initConfig() {
        setSize(Config.WIDTH, Config.HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void buildUi() {
        createFormPanel();
        createTable(new java.util.ArrayList<>()); 
        setVisible(true);
    }

    private void createFormPanel() {
        Panel form = new Panel();
        form.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        txtUser.setColumns(15);
        txtPassword.setColumns(15);

        form.add(txtUser);
        form.add(txtPassword);
        form.add(btnSave);
        form.add(btnClear);

        add(form, BorderLayout.NORTH);

        btnSave.addActionListener(e -> handleSave());
        btnClear.addActionListener(e -> handleClear());
    }

    private void createTable(List<Credential> creds) {
        String[] cols = { "Usuario", "Contraseña" };
        Object[][] data = buildTableData(creds);
        tablePanel = new SimpleTablePanel(cols, data);
        add(tablePanel, BorderLayout.CENTER);
    }

    private Object[][] buildTableData(List<Credential> list) {
        Object[][] data = new Object[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getUserName();
            data[i][1] = list.get(i).getPassword();
        }
        return data;
    }

    public void setSaveCallback(SaveCallback cb) {
        this.saveCallback = cb;
    }

    public void refreshTable(List<Credential> users) {
        remove(tablePanel);                       
        createTable(users);                      
        revalidate();
        repaint();
    }

    private void handleSave() {
        String user = txtUser.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Usuario y contraseña no pueden estar vacíos.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (saveCallback != null) {
            saveCallback.onSave(new Credential(user, pass));
        }
    }

    private void handleClear() {
        txtUser.setText("");
        txtPassword.setText("");
    }
}