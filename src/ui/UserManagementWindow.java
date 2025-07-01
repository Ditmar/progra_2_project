
package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;




public class UserManagementWindow extends JFrame {
    
 // Componentes
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnGuardar, btnLimpiar, btnBorrar,btnAgregar;
    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;


    public UserManagementWindow() {
        setTitle("Gestion de registro de usuarios");
        setSize(600, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // Panel superior - formulario
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        panelFormulario.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panelFormulario.add(txtUsuario);


        panelFormulario.add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField();
        panelFormulario.add(txtContrasena);


        btnGuardar = new JButton("Guardar usuario");
        btnLimpiar = new JButton("Limpiar formulario");
        
        panelFormulario.add(btnGuardar);
        panelFormulario.add(btnLimpiar);
        

        add(panelFormulario, BorderLayout.NORTH);


        // Panel central - tabla de usuarios
        String[] columnas = {"Usuario", "Contraseña"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaUsuarios = new JTable(modeloTabla);
        add(new JScrollPane(tablaUsuarios), BorderLayout.CENTER);


        // Acción del botón Guardar
        btnGuardar.addActionListener(e -> {
            String usuario = txtUsuario.getText();
            String contrasena = new String(txtContrasena.getPassword());


            if (!usuario.isEmpty() && !contrasena.isEmpty()) {
                modeloTabla.addRow(new Object[]{usuario, contrasena});
                JOptionPane.showMessageDialog(this, "Usuario guardado (simulado)");
            } else {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos.");
            }
        });


        
        btnLimpiar.addActionListener(e -> {
            txtUsuario.setText("");
            txtContrasena.setText("");

            modeloTabla.setRowCount(0);
        });


        setVisible(true);
    }


    public static void main(String[] args) {
        new UserManagementWindow();
    }

    

    
}

