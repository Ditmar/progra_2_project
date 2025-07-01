// Esta clase pertenece al paquete "bussines".
package bussines;

// Importaciones de todas las piezas que vamos a conectar.
import bussines.interfaces.HandleUserManagement; // El "contrato" que vamos a cumplir.
import bussines.model.Credential;
import services.UserService;
import ui.UserManagementWindow;

// Importamos herramientas de Swing.
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.List;

/**
 * Esta clase es el controlador. Es el intermediario.
 * Su trabajo es escuchar a la ventana y hablar con el servicio.
 * ✅ Cumple el Requisito 3 del examen.
 */
// "implements HandleUserManagement" significa que prometemos tener el método
// "onSaveUser".
public class UserManagement implements HandleUserManagement {

    // El controlador necesita una referencia a la ventana y al servicio que va a
    // manejar.
    private UserManagementWindow ventana;
    private UserService servicioDeUsuarios;

    public UserManagement() {
        // Creamos una instancia del servicio.
        this.servicioDeUsuarios = new UserService();

        // Es una buena práctica crear las ventanas de Swing de esta manera.
        // Asegura que el código de la UI se ejecuta en el hilo correcto.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 1. Creamos una instancia de nuestra ventana.
                ventana = new UserManagementWindow();

                // 2. Le decimos a la ventana que ESTA clase es su callback.
                ventana.setOnSaveCallback(UserManagement.this);

                // 3. Al iniciar, cargamos los usuarios que ya existen en la tabla.
                actualizarListaDeUsuariosEnLaVista();

                // 4. ¡CORRECCIÓN! Hacemos visible la ventana aquí, justo después de crearla.
                // Esto asegura que la ventana aparece, ya que el propio constructor
                // se encarga de todo el proceso.
                if (ventana != null) {
                    ventana.setVisible(true);
                }
            }
        });
    }

    // El método mostrarVentana() ha sido eliminado porque ya no es necesario.

    /**
     * ¡Este es el método que prometimos implementar!
     * Se ejecuta cuando el usuario hace clic en "Guardar" en la ventana.
     * Aquí va toda la lógica.
     * ✅ Cumple el Requisito 5 de validación.
     */
    @Override
    public void onSaveUser(Credential datosDelUsuario) {
        // Obtenemos los datos del objeto y eliminamos espacios en blanco del usuario.
        String usuario = datosDelUsuario.getUserName().trim();
        String password = datosDelUsuario.getPassword();

        // 1. Validación: ¿Están los campos vacíos?
        if (usuario.isEmpty() || password.isEmpty()) {
            ventana.mostrarMensaje("Error de Validación", "Por favor, complete todos los campos.",
                    JOptionPane.ERROR_MESSAGE);
            return; // Usamos 'return' para detener el método aquí y no seguir.
        }

        // 2. Validación: ¿Ya existe un usuario con el mismo nombre?
        if (this.servicioDeUsuarios.userExists(usuario)) {
            ventana.mostrarMensaje("Advertencia", "Ya existe un usuario con ese nombre. Se guardará de todos modos.",
                    JOptionPane.WARNING_MESSAGE);
        }

        // 3. Si todo está bien, le pedimos al servicio que guarde el usuario.
        // Creamos un nuevo objeto por si el original fue modificado (como con trim).
        this.servicioDeUsuarios.addUser(new Credential(usuario, password));

        // 4. Actualizamos la interfaz para que el usuario vea los cambios.
        this.actualizarListaDeUsuariosEnLaVista();
        this.ventana.limpiarFormulario();
        this.ventana.mostrarMensaje("Éxito", "Usuario guardado correctamente.", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método privado para mantener la tabla de la ventana siempre actualizada.
    private void actualizarListaDeUsuariosEnLaVista() {
        // 1. Pedimos la lista completa de usuarios al servicio.
        List<Credential> usuarios = this.servicioDeUsuarios.getAllUsers();
        // 2. Se la pasamos a la ventana para que la muestre.
        if (this.ventana != null) { // Buena práctica: asegurarse de que la ventana ya fue creada.
            this.ventana.actualizarTabla(usuarios);
        }
    }
}
