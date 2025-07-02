package services;

import bussines.model.Credential;
import java.util.*;
import java.util.stream.Collectors;

public class UserService {
    private final List<Credential> userList;

    public UserService() {
        userList = new ArrayList<>();
        initializeDefaultUsers();
    }

    private void initializeDefaultUsers() {
        addUser(new Credential("admin", "admin123"));
        addUser(new Credential("estudiante", "clave456"));
    }

    public void addUser(Credential user) {
        Objects.requireNonNull(user, "El usuario no puede ser nulo");
        userList.add(user);
    }

    public List<Credential> getAllUsers() {
        return Collections.unmodifiableList(userList);
    }

    public boolean userExists(String username) {
        return Optional.ofNullable(username)
            .filter(name -> !name.trim().isEmpty())
            .map(name -> userList.stream()
                .anyMatch(u -> u.getUserName().equalsIgnoreCase(name.trim())))
            .orElse(false);
    }
}