package bussines.interfaces;

import bussines.model.Credential;

public interface HandleUserManagement {
    void onSaveUser(Credential datosDelUsuario);
    void onDeleteUser(String userId);
}