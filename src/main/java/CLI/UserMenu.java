package CLI;

import entities.Ticket;
import util.DynamicArray;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class UserMenu {

    private static final Scanner keyboard = new Scanner(System.in);

    public static void show(String username) {
        while (true) {
            System.out.println("\n👤 Welcome, " + username + " (User)");
            System.out.println("1. Create a ticket");
            System.out.println("2. View my tickets");
            System.out.println("3. View ticket details by ID");
            System.out.println("0. Logout");
            System.out.print("Select an option: ");
            String option = keyboard.nextLine();

            switch (option) {
                case "1":
                    createTicket(username);
                    break;
                case "2":
                    viewMyTickets(username);
                    break;
                case "3":
                    viewTicketById(username);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void createTicket(String username) {
        System.out.print("Enter a short description of your issue: ");
        String description = keyboard.nextLine();

        int priority = 0;
        while (priority < 1 || priority > 5) {
            System.out.print("Enter priority (1–5): ");
            try {
                priority = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number between 1 and 5.");
            }
        }

        String id = UUID.randomUUID().toString();
        Ticket t = new Ticket(id, description, priority, LocalDateTime.now(), username, null, "Pending");

        MainMenu.getTickets().add(t); // Add to the global tickets list
        System.out.println("✅ Ticket created successfully. ID: " + id);
    }

    private static void viewMyTickets(String username) {
        DynamicArray<Ticket> allTickets = MainMenu.getTickets();
        boolean found = false;

        System.out.println("\n📋 Your Tickets:");
        for (int i = 0; i < allTickets.size(); i++) {
            Ticket t = allTickets.get(i);
            if (t.getReportingUsername().equals(username)) {
                System.out.println("- [" + t.getTicketId() + "] " + t.getIssueDescription() + " (Priority: " + t.getPriorityLevel() + ", Status: " + t.getStatus() + ")");
                found = true;
            }
        }

        if (!found) {
            System.out.println("You have not submitted any tickets yet.");
        }
    }

    private static void viewTicketById(String username) {
        System.out.print("Enter ticket ID: ");
        String ticketId = keyboard.nextLine();

        DynamicArray<Ticket> allTickets = MainMenu.getTickets();

        for (int i = 0; i < allTickets.size(); i++) {
            Ticket t = allTickets.get(i);
            if (t.getTicketId().equals(ticketId) && t.getReportingUsername().equals(username)) {
                System.out.println("\n🔍 Ticket Details:");
                System.out.println("ID: " + t.getTicketId());
                System.out.println("Description: " + t.getIssueDescription());
                System.out.println("Priority: " + t.getPriorityLevel());
                System.out.println("Created: " + t.getCreationDate());
                System.out.println("Status: " + t.getStatus());
                System.out.println("Assigned Agent: " + (t.getAllocatedAgentId() == null ? "Unassigned" : t.getAllocatedAgentId()));
                return;
            }
        }

        System.out.println("Ticket not found or does not belong to you.");
    }
}
