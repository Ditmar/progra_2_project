package services;

import java.util.ArrayList;
import java.util.List;

import bussines.model.Credential;

public class UserService {
    private List<Credential> usuarios;

    public UserService() {
        usuarios = new ArrayList<>();
    }

    public void addUser(Credential cred) {
        usuarios.add(cred);
    }

    public List<Credential> getAllUsers() {
        return new ArrayList<>(usuarios); 
    }
}