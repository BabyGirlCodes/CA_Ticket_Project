package CLI;

import entities.Ticket;
import entities.Agent;
import util.DynamicArray;

import java.util.Scanner;

public class AgentMenu {

    private static final Scanner keyboard = new Scanner(System.in);

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
