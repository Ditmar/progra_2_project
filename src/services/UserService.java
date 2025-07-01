// Le decimos a Java que esta clase pertenece al paquete "services".
package services;

// Necesitamos importar la clase "Credential" porque vamos a trabajar con ella.
// Está en el paquete "bussines.model".
import bussines.model.Credential;

// También importamos las herramientas de Java para crear y usar listas.
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase será nuestro pequeño "simulador de base de datos".
 * Su única responsabilidad es manejar una lista de usuarios.
 * No sabe nada sobre ventanas o botones.
 * ✅ Cumple el Requisito 2 del examen.
 */
public class UserService {

    // Creamos una lista para guardar objetos de tipo Credential.
    // Es 'private' porque ninguna otra clase debe poder acceder a ella
    // directamente.
    private List<Credential> listaDeUsuarios;

    // Este es el "constructor". Es un método especial que se ejecuta
    // automáticamente cuando creamos un nuevo UserService.
    public UserService() {
        // Aquí inicializamos la lista. Sin esta línea, la lista sería 'null' y nos
        // daría un error.
        this.listaDeUsuarios = new ArrayList<Credential>();

        // Para que la tabla no aparezca vacía, vamos a agregar un par de usuarios de
        // ejemplo.
        Credential usuario1 = new Credential("USERVIC", "user123");
        this.listaDeUsuarios.add(usuario1);

        Credential usuario2 = new Credential("Master", "manzana456");
        this.listaDeUsuarios.add(usuario2);
    }

    /**
     * Este método permite agregar un nuevo usuario a nuestra lista.
     * Es 'public' para que nuestro controlador pueda usarlo.
     * 
     * @param nuevoUsuario El objeto Credential que queremos añadir.
     */
    public void addUser(Credential nuevoUsuario) {
        this.listaDeUsuarios.add(nuevoUsuario);
    }

    /**
     * Este método devuelve la lista completa de usuarios.
     * Lo usaremos para mostrar todos los usuarios en la tabla.
     * 
     * @return La lista de objetos Credential.
     */
    public List<Credential> getAllUsers() {
        // Devolvemos la lista para que quien la pida pueda leerla.
        return this.listaDeUsuarios;
    }

    public boolean userExists(String usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'userExists'");
    }
}