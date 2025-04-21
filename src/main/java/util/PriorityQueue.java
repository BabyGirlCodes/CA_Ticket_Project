package util;

import enities.Ticket;

/**
 * A custom PriorityQueue that stores Ticket objects in descending order
 * of priority. Tickets with the same priority are ordered by insertion.
 */
public class PriorityQueue {

    private class Node {
        Ticket data;
        Node next;

        public Node(Ticket data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public PriorityQueue() {
        head = null;
        size = 0;
    }

    /**
     * Returns the number of tickets currently in the queue.
     * @return queue size
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the queue is empty.
     * @return true if the queue is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Removes all tickets from the queue.
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Returns the highest-priority ticket without removing it.
     * @return top ticket or null if empty
     */
    public Ticket peek() {
        if (head == null) {
            return null;
        }
        return head.data;
    }

    /**
     * Adds a new ticket to the queue based on its priority.
     * @param ticket the Ticket to be added
     */
    public void add(Ticket ticket) {
        Node newNode = new Node(ticket);

        if (head == null || ticket.compareTo(head.data) > 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null && ticket.compareTo(current.next.data) <= 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;

    }

    /**
     * Removes and returns the highest-priority ticket.
     * @return the removed ticket or null if empty
     */
    public Ticket remove() {
        if (head == null) {
            return null;
        }

        Ticket removedTicket = head.data;
        head = head.next;
        size--;
        return removedTicket;
    }


}
