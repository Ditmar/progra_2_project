package services;

import bussines.model.Credential;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<Credential> userList = new ArrayList<>();

    public void addUser(Credential cred) {
        userList.add(cred);
    }

    public List<Credential> getAllUsers() {
        return new ArrayList<>(userList);
    }
}