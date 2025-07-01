package services;
import java.util.ArrayList;
import java.util.List;
import bussines.model.Credencial;
import bussines.model.Credential;

public class UserService {
    private List<Credencial> users;
    public UserService(){
        this.users = new ArrayList<>();
        

    }
public void addUser(Credential cred) {
        users.add(cred);
    }

    public List<Credential> getAllUsers() {
        return new ArrayList<>(users); 
    }
}
    
