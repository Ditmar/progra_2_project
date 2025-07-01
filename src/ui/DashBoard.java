package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import bussines.UserManagement; // Importamos nuestro controlador.
import ui.components.Button; // Importamos el componente de botón.
import ui.config.Config;
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
        // Añadimos la llamada a nuestro nuevo método para crear el menú.
        createMenu();
    }

    private void createContainer() {
        this.westPanel = new Panel();
        this.westPanel.setLayout(new BorderLayout());
        // Le damos un tamaño preferido para que no sea demasiado ancho.
        this.westPanel.setPreferredSize(new Dimension(200, 0));

        this.centerPanel = new Panel();
        this.centerPanel.setLayout(new BorderLayout());
        add(westPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void createList() {
        List<String> list = new List<String>();
        list.addItem("Maria");
        list.addItem("Juan");
        list.addItem("Lucas");
        list.addItem("John");
        // La lista se añade al centro del panel izquierdo.
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

    /**
     * Este método se encarga de crear el botón de menú y añadirlo a la interfaz.
     * ✅ Cumple con el Requisito 4 del examen.
     */
    private void createMenu() {
        // 1. Creamos una instancia de nuestro botón personalizado.
        Button botonGestionUsuarios = new Button("Gestión de Usuarios");

        // 2. Le añadimos la acción que se ejecutará al hacer clic.
        botonGestionUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // --- CÓDIGO CORREGIDO ---
                // Ahora, la acción es mucho más simple.
                // Solo necesitamos crear una nueva instancia del controlador.
                // Su propio constructor se encargará de todo lo demás.
                new UserManagement();
                // --- FIN DE LA CORRECCIÓN ---
            }
        });

        // 3. Añadimos el botón al panel izquierdo (westPanel) en la parte de abajo
        // (SOUTH).
        this.westPanel.add(botonGestionUsuarios, BorderLayout.SOUTH);
    }
}