package services;

import bussines.model.Credential;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<Credential> users = new ArrayList<>();

    public void addUser(Credential cred) {
        users.add(cred);
    }

    public List<Credential> getAllUsers() {
        return new ArrayList<>(users);
    }

    public boolean userExists(String username) {
        return users.stream().anyMatch(u -> u.getUserName().equals(username));
    }
}