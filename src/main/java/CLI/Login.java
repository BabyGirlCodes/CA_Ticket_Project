package CLI;

import entities.Agent;
import entities.User;
import util.HashMap;

/**
 * Handles user authentication for the ticketing system.
 * Provides functionality to verify user credentials and determine user role.
 */
public class Login {

    /**
     * Authenticates a user based on their credentials and role.
     *
     * @param username the username to authenticate
     * @param password the password to verify
     * @param role the role of the user (either "user" or "agent")
     * @param users the map of regular users
     * @param agents the map of agents
     * @return true if authentication is successful, false otherwise
     */
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

