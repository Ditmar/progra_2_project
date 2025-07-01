package services;

public class LoginServices {

    public boolean isValidLogin(String email, String password) {
        if (!email.equals("GUNAR") && !password.equals("123")) {
            return false;
        }
        return true;
    }
}