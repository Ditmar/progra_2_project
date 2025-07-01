package services;

import bussines.model.Credential;
// import bussines.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<Credential> users= new ArrayList<>();

    public void addUser(Credential cred) {
        users.add(cred);
    }

    public List<Credential> getAllUsers() {
        return new ArrayList<>(users);
    }
}

