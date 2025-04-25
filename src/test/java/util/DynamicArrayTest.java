package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DynamicArrayTest {
    private DynamicArray<Integer> array;

    @BeforeEach
    void setUp() {
        array = new DynamicArray<>();
    }

    @Test
    void testAddAndGet() {
        array.add(1);
        array.add(2);
        array.add(3);

        assertEquals(3, array.size());
        assertEquals(1, array.get(0));
        assertEquals(2, array.get(1));
        assertEquals(3, array.get(2));
    }

    @Test
    void testRemove() {
        array.add(1);
        array.add(2);
        array.add(3);

        assertTrue(array.remove(Integer.valueOf(2)));
        assertEquals(2, array.size());
        assertEquals(1, array.get(0));
        assertEquals(3, array.get(1));
    }

    @Test
    void testRemoveAt() {
        array.add(1);
        array.add(2);
        array.add(3);

        assertEquals(2, array.removeAt(1));
        assertEquals(2, array.size());
        assertEquals(1, array.get(0));
        assertEquals(3, array.get(1));
    }

    @Test
    void testRemoveNonExistent() {
        array.add(1);
        array.add(2);

        assertFalse(array.remove(Integer.valueOf(3)));
        assertEquals(2, array.size());
    }

    @Test
    void testRemoveAtInvalidIndex() {
        array.add(1);
        array.add(2);

        assertThrows(IndexOutOfBoundsException.class, () -> array.removeAt(2));
    }

    @Test
    void testGetInvalidIndex() {
        array.add(1);
        array.add(2);

        assertThrows(IndexOutOfBoundsException.class, () -> array.get(2));
    }

    @Test
    void testResize() {
        // Test that the array resizes properly when capacity is reached
        for (int i = 0; i < 20; i++) {
            array.add(i);
        }
        assertEquals(20, array.size());
        for (int i = 0; i < 20; i++) {
            assertEquals(i, array.get(i));
        }
    }

    @Test
    void testClear() {
        array.add(1);
        array.add(2);
        array.add(3);

        array.clear();
        assertEquals(0, array.size());
        assertTrue(array.isEmpty());
    }

    @Test
    void testContains() {
        array.add(1);
        array.add(2);
        array.add(3);

        assertTrue(array.contains(2));
        assertFalse(array.contains(4));
    }

    @Test
    void testIterator() {
        array.add(1);
        array.add(2);
        array.add(3);

        int sum = 0;
        for (Integer num : array) {
            sum += num;
        }
        assertEquals(6, sum);
    }
} 