package CLI;

import java.util.Scanner;

public class MainMenu {

    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Ticket Support System");

        while (true) {
            System.out.println("\nAre you a:");
            System.out.println("1. User");
            System.out.println("2. Agent");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            String choice = keyboard.nextLine();

            switch (choice) {
                case "1":
                    handleLogin("user");
                    break;
                case "2":
                    handleLogin("agent");
                    break;
                case "0":
                    System.out.println("👋 Exiting. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void handleLogin(String role) {
        System.out.print("Enter username: ");
        String username = keyboard.nextLine();

        System.out.print("Enter password: ");
        String password = keyboard.nextLine();

        if (Login.login(username, password, role)) {
            System.out.println("✅ Login successful as " + role);
            if (role.equals("user")) {
                UserMenu.show(username);
            } else {
                AgentMenu.show(username);
            }
        } else {
            System.out.println("❌ Login failed. Please try again.");
        }
    }
}

