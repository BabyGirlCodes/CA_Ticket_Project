package CLI;

import entities.Agent;
import entities.User;
import util.HashMap;


public class Login {

    public static boolean login(String username, String password, String role, HashMap<String, User> users, HashMap<String, Agent> agents) {
        if (role.equalsIgnoreCase("user")) {
            User u = users.get(username);
            return u != null && u.getPassword().equals(password);
        } else {
            Agent a = agents.get(username);
            return a != null && a.getPassword().equals(password);
        }
    }
}

