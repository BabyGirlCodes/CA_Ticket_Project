package util;

import enities.Ticket;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {


        @Test
        void testSize_EmptyQueue() {
            // SET UP:
            PriorityQueue queue = new PriorityQueue();
            int expResult = 0;

            // LOGIC:
            int result = queue.size();

            // ASSERTIONS:
            assertEquals(expResult, result, "Size of empty queue should be 0");
        }

        @Test
        void testIsEmpty_EmptyQueue() {
            // SET UP:
            PriorityQueue queue = new PriorityQueue();
            boolean expResult = true;

            // LOGIC:
            boolean result = queue.isEmpty();

            // ASSERTIONS:
            assertEquals(expResult, result, "isEmpty() should return true for an empty queue");
        }
    @Test
    void testAdd_HighestPriorityFirst() {
        // SET UP:
        PriorityQueue queue = new PriorityQueue();
        LocalDateTime now = LocalDateTime.now();

        Ticket t1 = new Ticket("1", "Low", 1, now, "user", null, "Pending");
        Ticket t2 = new Ticket("2", "High", 5, now, "user", null, "Pending");

        // LOGIC:
        queue.add(t1);
        queue.add(t2);
        Ticket actual = queue.peek();
        String actualId = actual.getTicketId();

        // EXPECTED:
        String expectedId = "2";  // ticket with higher priority should be at the head

        // ASSERTIONS:
        assertEquals(expectedId, actualId, "Highest priority ticket should be at the head");
    }


        @Test
        void testRemove_FromQueue() {
            // SET UP:
            PriorityQueue queue = new PriorityQueue();
            Ticket t1 = new Ticket("1", "Priority 2", 2, LocalDateTime.now(), "user1", null, "Pending");
            Ticket t2 = new Ticket("2", "Priority 5", 5, LocalDateTime.now(), "user2", null, "Pending");

            queue.add(t1);
            queue.add(t2);

            // LOGIC:
            Ticket removed = queue.remove();

            // ASSERTIONS:
            assertEquals("2", removed.getTicketId(), "Removed ticket should be the one with highest priority");
        }

        @Test
        void testPeek_EmptyQueue() {
            // SET UP:
            PriorityQueue queue = new PriorityQueue();

            // ASSERTIONS:
            assertNull(queue.peek(), "Peek should return null for empty queue");
        }

        @Test
        void testRemove_EmptyQueue() {
            // SET UP:
            PriorityQueue queue = new PriorityQueue();

            // ASSERTIONS:
            assertNull(queue.remove(), "Remove should return null when the queue is empty");
        }

        @Test
        void testSamePriorityOrder_UsesInsertionOrder() {
            // SET UP:
            PriorityQueue queue = new PriorityQueue();
            LocalDateTime now = LocalDateTime.now();
            Ticket t1 = new Ticket("1", "P3", 3, now, "user1", null, "Pending");
            Ticket t2 = new Ticket("2", "P3", 3, now.plusSeconds(1), "user2", null, "Pending");

            queue.add(t1);
            queue.add(t2);

            // LOGIC:
            Ticket firstOut = queue.remove();

            // ASSERTIONS:
            assertEquals("1", firstOut.getTicketId(), "When priorities match, insertion order should be preserved");
        }

        @Test
        void testClear() {
            // SET UP:
            PriorityQueue queue = new PriorityQueue();
            Ticket t1 = new Ticket("1", "Any", 2, LocalDateTime.now(), "user", null, "Pending");
            queue.add(t1);
            queue.clear();
            assertTrue(queue.isEmpty(), "Queue should be empty after clear");
            assertEquals(0, queue.size(), "Queue size should be 0 after clear");
        }
    }


