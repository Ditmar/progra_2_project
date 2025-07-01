package services;

import bussines.model.Credential;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<Credential> users;
    
    public UserService() {
        this.users = new ArrayList<>();
        users.add(new Credential("admin", "admin123"));
        users.add(new Credential("user1", "password1"));
    }
    
    public void addUser(Credential credential) {
        if (credential != null && 
            credential.getUsername() != null && 
            !credential.getUsername().isEmpty()) {
            users.add(credential);
        }
    }
    
    public List<Credential> getAllUsers() {
        return new ArrayList<>(users);
    }
    
    public boolean userExists(String username) {
        return users.stream()
                   .anyMatch(user -> user.getUsername().equals(username));
    }
    
    public int getUserCount() {
        return users.size();
    }
}
