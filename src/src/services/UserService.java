package services;

import java.util.ArrayList;
import java.util.List;
import bussines.model.Credential;

public class UserService {
    private final List<Credential> users = new ArrayList<>();

    public void addUser(Credential cred) {
        users.add(cred);
    }

    public List<Credential> getAllUsers() {
        return users;
    }
}