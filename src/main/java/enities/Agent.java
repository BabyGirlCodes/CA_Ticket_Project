package enities;

import java.util.Objects;

/**
 * Represents an agent in the ticketing system.
 * Agents are users with additional fields such as agent ID and name.
 */
public class Agent extends User {
    private String agentId;
    private String agentName;

    // Niharika this part contains linked lists for you
    private Object openTickets;
    private Object closedTickets;

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
        this.openTickets = null;   // Will be initialized with LinkedList
        this.closedTickets = null; // Will be initialized with LinkedList
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
     * Determines equality between two enities.Agent objects based on their agent ID.
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
     * Generates a hash code for the enities.Agent using a custom formula
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
     * Returns a string representation of the enities.Agent.
     *
     * @return A formatted string with agent ID and name.
     */
    @Override
    public String toString() {
        return "enities.Agent{" + "ID='" + agentId + "', name='" + agentName + "'}";
    }
}
