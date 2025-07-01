// Las interfaces, como la de HandleLogin, van en este paquete.
package bussines.interfaces;

// Importamos Credential porque es el tipo de dato que pasaremos.
import bussines.model.Credential;

/**
 * Esta interfaz es un contrato que obliga a nuestro controlador
 * (UserManagement)
 * a tener un método llamado "onSaveUser".
 * La ventana usará este contrato para "llamar" al controlador sin saber quién
 * es exactamente.
 */
public interface HandleUserManagement {

    // Cualquier clase que implemente esta interfaz DEBE tener este método.
    void onSaveUser(Credential datosDelUsuario);
}