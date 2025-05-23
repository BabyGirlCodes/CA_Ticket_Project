package CLI;

import entities.User;
import entities.Agent;
import entities.Ticket;
import util.HashMap;
import util.DynamicArray;
import util.fileUtil;

import java.util.Scanner;

/**
 * Main menu class for the Ticket Support System.
 * Handles the initial program flow, user authentication, and data persistence.
 */
public class MainMenu {

    private static final Scanner keyboard = new Scanner(System.in);

    private static HashMap<String, User> users;
    private static HashMap<String, Agent> agents;
    private static DynamicArray<Ticket> tickets;

    /**
     * Main entry point for the Ticket Support System.
     * Loads data, handles user authentication, and directs to appropriate menu.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        loadData();

        System.out.println("Welcome to the Ticket Support System");

        String username = handleLogin();
        if (username == null) {
            saveData();
            System.out.println("👋 Exiting. Goodbye!");
            return;
        }

        if (agents.containsKey(username)) {
            System.out.println("\n🛠️ Welcome, " + username + " (Agent)");
            AgentMenu.show(username);
        } else {
            System.out.println("\n👤 Welcome, " + username + " (User)");
            UserMenu.show(username);
        }

        saveData();
        System.out.println("👋 Exiting. Goodbye!");
    }

    /**
     * Returns the collection of all tickets in the system.
     *
     * @return the DynamicArray containing all tickets
     */
    public static DynamicArray<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Returns the map of all agents in the system.
     *
     * @return the HashMap containing all agents
     */
    public static util.HashMap<String, entities.Agent> getAgents() {
        return agents;
    }

    /**
     * Handles the login and registration process.
     * Provides options for login, registration, and exit.
     *
     * @return the username of the authenticated user, or null if user chooses to exit
     */
    private static String handleLogin() {
        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            String option = keyboard.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Enter username: ");
                    String username = keyboard.nextLine();
                    System.out.print("Enter password: ");
                    String password = keyboard.nextLine();

                    if (agents.containsKey(username)) {
                        Agent a = agents.get(username);
                        if (a.getPassword().equals(password)) return username;
                    } else if (users.containsKey(username)) {
                        User u = users.get(username);
                        if (u.getPassword().equals(password)) return username;
                    }

                    System.out.println("❌ Login failed. Please try again.");
                    break;

                case "2":
                    System.out.print("Choose a username: ");
                    String newUsername = keyboard.nextLine();
                    System.out.print("Choose a password: ");
                    String newPassword = keyboard.nextLine();
                    System.out.print("Are you registering as a user or agent? ");
                    String role = keyboard.nextLine().toLowerCase();

                    if (role.equals("agent")) {
                        System.out.print("Enter agent ID: ");
                        String agentId = keyboard.nextLine();
                        System.out.print("Enter agent name: ");
                        String agentName = keyboard.nextLine();
                        agents.put(newUsername, new Agent(newUsername, newPassword, agentId, agentName));
                    } else {
                        users.put(newUsername, new User(newUsername, newPassword));
                    }

                    System.out.println("✅ Registered successfully. Please login.");
                    break;

                case "0":
                    return null;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Loads user, agent, and ticket data from text files.
     * Initializes the system's data structures with the loaded data.
     */
    private static void loadData() {
        users = new HashMap<>();
        for (User u : fileUtil.readUsers("users.txt")) {
            users.put(u.getUsername(), u);
        }

        agents = new HashMap<>();
        for (Agent a : fileUtil.readAgents("agents.txt")) {
            agents.put(a.getUsername(), a);
        }

        tickets = new DynamicArray<>();
        for (Ticket t : fileUtil.readTickets("tickets.txt")) {
            tickets.add(t);
        }

        System.out.println("✅ Data loaded from text files.");
    }

    /**
     * Saves the current state of user, agent, and ticket data to text files.
     * Ensures data persistence between program sessions.
     */
    private static void saveData() {
        Object[] userObjs = users.getValues();
        User[] userArr = new User[userObjs.length];
        for (int i = 0; i < userArr.length; i++) {
            userArr[i] = (User) userObjs[i];
        }

        Object[] agentObjs = agents.getValues();
        Agent[] agentArr = new Agent[agentObjs.length];
        for (int i = 0; i < agentArr.length; i++) {
            agentArr[i] = (Agent) agentObjs[i];
        }

        Object[] ticketObjs = tickets.getValues();
        Ticket[] ticketArr = new Ticket[ticketObjs.length];
        for (int i = 0; i < ticketArr.length; i++) {
            ticketArr[i] = (Ticket) ticketObjs[i];
        }

        fileUtil.saveUsers("users.txt", userArr);
        fileUtil.saveAgents("agents.txt", agentArr);
        fileUtil.saveTickets("tickets.txt", ticketArr);

        System.out.println("💾 Data saved to text files.");
    }
}
