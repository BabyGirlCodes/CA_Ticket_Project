package CLI;



public class Login {
    public static boolean login(String username, String password, String role) {
        return username.equals(password);
    }
}
