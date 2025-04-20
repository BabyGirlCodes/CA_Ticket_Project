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
     * Constructs a new entity.Ticket with all required fields.
     *
     * @param ticketId           The unique ticket ID.
     * @param issueDescription   The description of the issue.
     * @param priorityLevel      The priority level (1–5).
     * @param creationDate       The date and time the ticket was created.
     * @param reportingUsername  The username of the reporting user.
     * @param allocatedAgentId   The ID of the assigned agent (nullable).
     * @param status             The current status of the ticket.
     */
    public Ticket(String ticketId, String issueDescription, int priorityLevel,
                  LocalDateTime creationDate, String reportingUsername,
                  String allocatedAgentId, String status) {

        if (priorityLevel < 1 || priorityLevel > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5.");
        }
        if (ticketId == null || issueDescription == null || creationDate == null || reportingUsername == null || status == null) {
            throw new IllegalArgumentException("Fields cannot be null.");
        }

        this.ticketId = ticketId;
        this.issueDescription = issueDescription;
        this.priorityLevel = priorityLevel;
        this.creationDate = creationDate;
        this.reportingUsername = reportingUsername;
        this.allocatedAgentId = allocatedAgentId;
        this.status = status;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getReportingUsername() {
        return reportingUsername;
    }

    public String getAllocatedAgentId() {
        return allocatedAgentId;
    }

    public String getStatus() {
        return status;
    }

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
        if (this.priorityLevel != other.priorityLevel) {
            return Integer.compare(other.priorityLevel, this.priorityLevel); // higher priority first
        }
        return this.creationDate.compareTo(other.creationDate); // older first
    }

    /**
     * Tickets are equal if their ticket IDs match.
     *
     * @param obj The object to compare.
     * @return true if equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ticket)) return false;
        Ticket other = (Ticket) obj;
        return this.ticketId.equals(other.ticketId);
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
