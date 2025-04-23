package entities;

import util.DynamicArray;
import util.PriorityQueue;
import java.util.Objects;

/**
 * Represents an agent in the ticketing system.
 * Agents are users with additional fields such as agent ID and name.
 */
public class Agent extends User {
    private String agentId;
    private String agentName;



    // PriorityQueue for managing assigned tickets by priority
    private PriorityQueue assignedTickets = new PriorityQueue();
    private DynamicArray<Ticket> openTickets = new DynamicArray<>();
    private DynamicArray<Ticket> closedTickets = new DynamicArray<>();

    /**
     * Constructs a new enities.Agent with the given user credentials and agent-specific details.
     *
     * @param username   The agent's username (inherited from enities.User).
     * @param password   The agent's password (inherited from enities.User).
     * @param agentId    The unique ID for the agent.
     * @param agentName  The full name of the agent.
     */
    public Agent(String username, String password, String agentId, String agentName) {
        super(username, password);
        this.agentId = agentId;
        this.agentName = agentName;
    }

    /**
     * Returns the agent's unique ID.
     *
     * @return The agent ID.
     */
    public String getAgentId() {
        return agentId;
    }

    /**
     * Returns the agent's name.
     *
     * @return The agent name.
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * Returns the priority queue of tickets assigned to this agent.
     *
     * @return the agent's assigned ticket queue.
     */
    public PriorityQueue getAssignedTickets() {
        return assignedTickets;
    }
    /**
     * Returns the array of opened tickets assigned to this agent.
     *
     * @return the agent's opened tickets.
     */
    public DynamicArray<Ticket> getOpenTickets() {
        return openTickets;
    }
    /**
     * Returns the array of closed tickets assigned to this agent.
     *
     * @return the agent's closed tickets.
     */
    public DynamicArray<Ticket> getClosedTickets() {
        return closedTickets;
    }

    /**
     * Adds a ticket to the agent's priority queue.
     *
     * @param ticket the ticket to add
     */
    public void addTicket(Ticket ticket) {

        assignedTickets.add(ticket);
        openTickets.add(ticket);
    }

    /**
     * @param ticket   The ticket to be closed
     */
    public void closeTicket(Ticket ticket) {
        // Remove from openTickets
        for (int i = 0; i < openTickets.size(); i++) {
            if (openTickets.get(i).equals(ticket)) {
                openTickets.removeAt(i);
                break;
            }
        }
        // Add to closedTickets
        ticket.setStatus("Closed");
        closedTickets.add(ticket);
    }

    /**
     * Removes and returns the highest-priority ticket from the queue.
     *
     * @return the ticket with the highest priority
     */
    public Ticket handleNextTicket() {
        return (Ticket) assignedTickets.remove();
    }

    /**
     * Determines equality between two entities.Agent objects based on their agent ID.
     *
     * @param obj The object to compare with this agent.
     * @return true if the agent IDs match; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Agent)) return false;
        Agent other = (Agent) obj;
        return this.agentId.equals(other.agentId);
    }

    /**
     * Generates a hash code for the entities.Agent using a custom formula
     * that includes the agentId and agentName fields.
     * This reduces the chances of hash collisions.
     *
     * @return A hash code value for the agent.
     */
    @Override
    public int hashCode() {
        int hash = 13;
        hash = hash + (11 * Objects.hash(this.agentId));
        hash = hash + (11 * Objects.hash(this.agentName));
        return hash;
    }

    /**
     * Returns a string representation of the entities.Agent.
     *
     * @return A formatted string with agent ID and name.
     */
    @Override
    public String toString() {
        return "entities.Agent{" + "ID='" + agentId + "', name='" + agentName + "'}";
    }
}
