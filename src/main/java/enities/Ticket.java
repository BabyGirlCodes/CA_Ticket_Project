package enities;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a support ticket submitted by a user.
 * Tickets have a priority, status, and are assigned to an agent.
 */
public class Ticket implements Comparable<Ticket> {

    private String ticketId;
    private String issueDescription;
    private int priorityLevel; // 1 (lowest) to 5 (highest)
    private LocalDateTime creationDate;
    private String reportingUsername;
    private String allocatedAgentId;
    private String status; // e.g., Pending, In Progress, Solved, Stalled

    /**
     * Constructs a new Ticket using setter methods for validation and consistency.
     *
     * @param ticketId           The unique ID of the ticket.
     * @param issueDescription   A description of the issue.
     * @param priorityLevel      The priority level (1 to 5).
     * @param creationDate       The creation date and time of the ticket.
     * @param reportingUsername  The username of the reporting user.
     * @param allocatedAgentId   The ID of the agent assigned to the ticket (can be null).
     * @param status             The current status of the ticket.
     * @throws IllegalArgumentException if required fields are null or priority is out of range.
     */
    public Ticket(String ticketId, String issueDescription, int priorityLevel,
                  LocalDateTime creationDate, String reportingUsername,
                  String allocatedAgentId, String status) {
        if (ticketId == null || issueDescription == null || creationDate == null
                || reportingUsername == null || status == null) {
            throw new IllegalArgumentException("Required fields cannot be null.");
        }

        setTicketId(ticketId);
        setIssueDescription(issueDescription);
        setPriorityLevel(priorityLevel);
        setCreationDate(creationDate);
        setReportingUsername(reportingUsername);
        setAllocatedAgentId(allocatedAgentId); // optional — can be null
        setStatus(status);
    }




    /**
     * @return The id of the ticket being issued
     */
    public String getTicketId() {
        return ticketId;
    }

    /**
     * @return The description of the issue
     */
    public String getIssueDescription() {
        return issueDescription;
    }

    /**
     * @return Gets the priority Level of the ticket
     */
    public int getPriorityLevel() {
        return priorityLevel;
    }

    /**
     * @return Gets the creation date of the ticket
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return Gets the reporting user's username
     */
    public String getReportingUsername() {
        return reportingUsername;
    }

    /**
     * @return Gets the Allocated agent's Id
     */
    public String getAllocatedAgentId() {
        return allocatedAgentId;
    }

    /**
     * @return Gets the status of the ticket
     */
    public String getStatus() {
        return status;
    }
    /**
     * Sets the unique ID of the ticket.
     *
     * @param ticketId The unique ticket ID to set.
     */
    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * Sets the description of the issue reported in the ticket.
     *
     * @param issueDescription The issue description to set.
     */
    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    /**
     * Sets the priority level of the ticket.
     * Must be an integer between 1 and 5.
     *
     * @param priorityLevel The priority level to set.
     * @throws IllegalArgumentException if priorityLevel is not between 1 and 5.
     */
    public void setPriorityLevel(int priorityLevel) {
        if (priorityLevel < 1 || priorityLevel > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5.");
        }
        this.priorityLevel = priorityLevel;
    }

    /**
     * Sets the creation date and time of the ticket.
     *
     * @param creationDate The LocalDateTime the ticket was created.
     */
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Sets the username of the user who reported the ticket.
     *
     * @param reportingUsername The username of the reporting user.
     */
    public void setReportingUsername(String reportingUsername) {
        this.reportingUsername = reportingUsername;
    }

    /**
     * Sets the ID of the agent to whom the ticket is assigned.
     *
     * @param allocatedAgentId The ID of the assigned agent.
     */
    public void setAllocatedAgentId(String allocatedAgentId) {
        this.allocatedAgentId = allocatedAgentId;
    }


    /**
     * @param status Takes in the status of the ticket
     */
    public void setStatus(String status) {
        if (status != null) {
            this.status = status;
        }
    }

    /**
     * Compares two tickets first by priority (descending),
     * then by creation date (ascending) if priority is equal.
     *
     * @param other The ticket to compare to.
     * @return A negative integer, zero, or a positive integer depending on order.
     */
    @Override
    public int compareTo(Ticket other) {
        // Compare by priority first
        int priorityComparison = Integer.compare(this.priorityLevel, other.priorityLevel);
        if (priorityComparison != 0) {
            return priorityComparison;
        }
        // If priorities are the same, compare by creation time (earlier first)
        return this.creationDate.compareTo(other.creationDate);
    }


    /**
     * Tickets are equal if their ticket IDs match.
     *
     * @param o The object to compare.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }

        Ticket other = (Ticket) o;

        // Two tickets are equal if their ticketId values match
        return Objects.equals(this.ticketId, other.ticketId);
    }

    /**
     * Generates a custom hash code using different fields.
     *
     * @return The ticket's hash code.
     */
    @Override
    public int hashCode() {
        int hash = 13;
        hash = hash + (11 * Objects.hash(this.ticketId));
        hash = hash + (11 * Objects.hash(this.priorityLevel));
        hash = hash + (11 * Objects.hash(this.creationDate));
        return hash;
    }

    /**
     * Returns a readable string for the ticket.
     *
     * @return A string summary of the ticket.
     */
    @Override
    public String toString() {
        return "enities.Ticket{" +
                "ID='" + ticketId + '\'' +
                ", Priority=" + priorityLevel +
                ", Date=" + creationDate +
                ", Status='" + status + '\'' +
                ", enities.User='" + reportingUsername + '\'' +
                ", enities.Agent='" + allocatedAgentId + '\'' +
                '}';
    }
}
