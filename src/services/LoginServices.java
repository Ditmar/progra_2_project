package services;

public class LoginServices {

    public boolean isValidLogin(String email, String password) {
        if (!email.equals("Mauricio") && !password.equals("sis211")) {
            return false;
        }
        return true;
    }
}
