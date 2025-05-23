package CLI;

import entities.Ticket;
import entities.Agent;
import util.DynamicArray;

import java.util.Scanner;

/**
 * Provides the user interface for agents in the Ticket Support System.
 * Handles ticket assignment, management, and resolution for agents.
 */
public class AgentMenu {

    private static final Scanner keyboard = new Scanner(System.in);

    /**
     * Displays the main menu for agents and handles their interactions.
     *
     * @param agentUsername the username of the logged-in agent
     */
    public static void show(String agentUsername) {
        Agent agent = MainMenu.getAgents().get(agentUsername);

        while (true) {
            System.out.println("\n🛠️ Agent Menu for " + agentUsername);
            System.out.println("1. View assigned (open) tickets");
            System.out.println("2. Close a ticket");
            System.out.println("3. View closed tickets");
            System.out.println("4. Auto-assign highest priority ticket");
            System.out.println("0. Logout");
            System.out.print("Select an option: ");
            String option = keyboard.nextLine();

            switch (option) {
                case "1":
                    viewAssignedTickets(agent);
                    break;
                case "2":
                    closeTicket(agent);
                    break;
                case "3":
                    viewClosedTickets(agent);
                    break;
                case "4":
                    autoAssignTicket(agent, agentUsername);
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    /**
     * Displays all tickets currently assigned to the agent.
     * Shows ticket ID, description, and status.
     *
     * @param agent the agent whose tickets to display
     */
    private static void viewAssignedTickets(Agent agent) {
        DynamicArray<Ticket> open = agent.getOpenTickets();
        if (open.size() == 0) {
            System.out.println("📭 No assigned tickets.");
            return;
        }

        System.out.println("📋 Open Tickets:");
        for (int i = 0; i < open.size(); i++) {
            Ticket t = open.get(i);
            System.out.println("- [" + t.getTicketId() + "] " + t.getIssueDescription() + " (Status: " + t.getStatus() + ")");
        }
    }

    /**
     * Closes a ticket that is assigned to the agent.
     * Moves the ticket from open to closed status.
     *
     * @param agent the agent closing the ticket
     */
    private static void closeTicket(Agent agent) {
        System.out.print("Enter the ticket ID to close: ");
        String id = keyboard.nextLine();

        DynamicArray<Ticket> open = agent.getOpenTickets();

        for (int i = 0; i < open.size(); i++) {
            Ticket t = open.get(i);
            if (t.getTicketId().equals(id)) {
                agent.closeTicket(t);
                System.out.println("✅ Ticket " + id + " closed.");
                return;
            }
        }

        System.out.println("❌ Ticket not found in your assigned open tickets.");
    }

    /**
     * Displays all tickets that have been closed by the agent.
     * Shows ticket ID and description.
     *
     * @param agent the agent whose closed tickets to display
     */
    private static void viewClosedTickets(Agent agent) {
        DynamicArray<Ticket> closed = agent.getClosedTickets();
        if (closed.size() == 0) {
            System.out.println("📭 No closed tickets.");
            return;
        }

        System.out.println("📁 Closed Tickets:");
        for (int i = 0; i < closed.size(); i++) {
            Ticket t = closed.get(i);
            System.out.println("- [" + t.getTicketId() + "] " + t.getIssueDescription());
        }
    }

    /**
     * Automatically assigns the highest priority unassigned ticket to the agent.
     * Updates ticket status and adds it to the agent's assigned tickets.
     *
     * @param agent the agent to assign the ticket to
     * @param agentUsername the username of the agent
     */
    private static void autoAssignTicket(Agent agent, String agentUsername) {
        DynamicArray<Ticket> all = MainMenu.getTickets();
        Ticket topTicket = null;

        for (int i = 0; i < all.size(); i++) {
            Ticket t = all.get(i);
            if (t.getAllocatedAgentId() == null || t.getAllocatedAgentId().isEmpty()) {
                if (topTicket == null || t.compareTo(topTicket) > 0) {
                    topTicket = t;
                }
            }
        }

        if (topTicket != null) {
            topTicket.setAllocatedAgentId(agentUsername);
            topTicket.setStatus("In progress");
            agent.addTicket(topTicket);
            System.out.println("✅ Ticket " + topTicket.getTicketId() + " auto-assigned to you.");
        } else {
            System.out.println("❌ No unassigned tickets available.");
        }
    }
}
