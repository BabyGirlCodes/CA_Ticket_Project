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


}
