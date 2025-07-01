// Esta clase pertenece al paquete de la interfaz de usuario.
package ui;

// Importaciones de Swing (para ventanas, botones, etc.) y AWT (para layouts y eventos).
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Importaciones de nuestras propias clases.
import bussines.interfaces.HandleUserManagement;
import bussines.model.Credential;
import ui.components.Button; // ¡Usamos los componentes del docente!
import ui.components.PasswordField;
import ui.components.SimpleTablePanel;
import ui.components.TextField;

/**
 * Esta clase es la ventana para gestionar usuarios.
 * Solo se encarga de la parte visual.
 * ✅ Cumple el Requisito 1 y 6 del examen.
 */
public class UserManagementWindow extends JFrame {

    // Declaramos los componentes que vamos a usar en la ventana.
    private SimpleTablePanel panelDeLaTabla;
    private TextField campoUsuario;
    private PasswordField campoPassword;
    private Button botonGuardar;
    private Button botonLimpiar;

    // Esta variable guardará una referencia a nuestro "contrato" (el callback).
    private HandleUserManagement callback;

    public UserManagementWindow() {
        super("Gestión de Usuarios"); // Llama al constructor de JFrame para poner el título.

        // --- Configuración básica de la ventana ---
        this.setSize(700, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana.
        this.setLocationRelativeTo(null); // La centra en la pantalla.

        // --- Organización de la ventana (Layouts) ---
        // Usaremos BorderLayout para dividir la ventana en zonas (Norte, Sur, Centro,
        // etc.).
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10)); // Layout con 10px de separación.
        panelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15)); // Un margen de 15px.
        this.setContentPane(panelPrincipal);

        // --- 1. La Tabla (arriba, en la zona central) ---
        String[] nombresDeColumnas = { "Nombre de Usuario" };
        Object[][] datosInicialesVacios = new Object[0][1]; // 0 filas, 1 columna.
        this.panelDeLaTabla = new SimpleTablePanel(nombresDeColumnas, datosInicialesVacios);
        this.panelDeLaTabla.setBorder(BorderFactory.createTitledBorder("Usuarios Registrados"));
        panelPrincipal.add(this.panelDeLaTabla, BorderLayout.CENTER);

        // --- 2. El Formulario (abajo, en la zona sur) ---
        JPanel panelFormulario = new JPanel(new GridBagLayout()); // GridBagLayout es bueno para formularios.
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Agregar Nuevo Usuario"));
        GridBagConstraints gbc = new GridBagConstraints(); // Objeto para configurar la posición.
        gbc.insets = new Insets(5, 5, 5, 5); // Margen de 5px para cada elemento.
        gbc.fill = GridBagConstraints.HORIZONTAL; // Para que los campos de texto se estiren.
        gbc.weightx = 1.0; // Damos peso a la columna de los campos de texto para que se expanda.

        // Añadimos la etiqueta "Usuario:"
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0; // La etiqueta no se expande.
        panelFormulario.add(new JLabel("Usuario:"), gbc);

        // Añadimos el campo de texto para el usuario, pasándole el texto del
        // placeholder.
        this.campoUsuario = new TextField("Ingrese el nombre de usuario");

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1; // El campo de texto sí se expande.
        panelFormulario.add(this.campoUsuario, gbc);

        // Añadimos la etiqueta "Contraseña:"
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Contraseña:"), gbc);

        // Añadimos el campo de contraseña, pasándole el texto del placeholder.
        this.campoPassword = new PasswordField("Ingrese la contraseña");

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        panelFormulario.add(this.campoPassword, gbc);

        // --- 3. Los Botones ---
        // Aquí también había un error, se estaba usando new Button(label) pero el
        // constructor de Button del profesor puede que no necesite un label, sino
        // que lo establezca después. Suponiendo que sí lo necesita:
        this.botonGuardar = new Button("Guardar Usuario");
        this.botonLimpiar = new Button("Limpiar Formulario");

        // Creamos un panel solo para los botones, para que se alineen bien a la
        // derecha.
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(this.botonGuardar);
        panelBotones.add(this.botonLimpiar);

        // Añadimos el panel de botones al formulario.
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panelFormulario.add(panelBotones, gbc);

        panelPrincipal.add(panelFormulario, BorderLayout.SOUTH);

        // --- 4. Añadir "Listeners" a los botones (qué hacer al hacer clic) ---
        this.botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (callback != null) {
                    String usuario = new String(campoUsuario.getText()).trim();
                    String password = new String(campoPassword.getPassword());
                    Credential datos = new Credential(usuario, password);
                    callback.onSaveUser(datos);
                }
            }
        });

        this.botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
            }
        });
    }

    // --- Métodos públicos para que el controlador pueda manipular la ventana ---
    public void setOnSaveCallback(HandleUserManagement callback) {
        this.callback = callback;
    }

    public void limpiarFormulario() {
        this.campoUsuario.setText(""); // Esto podría reactivar el placeholder si el campo queda vacío.
        this.campoPassword.setText("");
    }

    public void mostrarMensaje(String titulo, String mensaje, int tipoDeMensaje) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipoDeMensaje);
    }

    public void actualizarTabla(List<Credential> listaDeUsuarios) {
        DefaultTableModel modelo = this.panelDeLaTabla.getModel();
        modelo.setRowCount(0);

        for (Credential usuario : listaDeUsuarios) {
            Object[] fila = { usuario.getUserName() };
            modelo.addRow(fila);
        }
    }
}