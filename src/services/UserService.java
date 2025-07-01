package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bussines.model.Credential;

public class UserService {
    private List<Credential> users;

    public UserService() {
        this.users = new ArrayList<>();
        this.users.add(new Credential("admin", "admin123"));
        this.users.add(new Credential("test", "1234"));
    }

    public void addUser(Credential cred) {
        if (cred == null) {
            throw new IllegalArgumentException("Credential no puede ser nulo");
        }
        this.users.add(cred);
    }

    public List<Credential> getAllUsers() {
        return Collections.unmodifiableList(this.users);
    }
}
