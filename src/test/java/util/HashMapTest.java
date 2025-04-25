package util;

import entities.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class HashMapTest {

    @Test
    void testPutAndGet() {
        HashMap map = new HashMap();
        map.put("one", 1);
        map.put("two", 2);

        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
        assertNull(map.get("three"));
    }

    @Test
    void testUpdateValue() {
        HashMap map = new HashMap();
        map.put("a", 100);
        map.put("a", 200);  // update value

        assertEquals(200, map.get("a"));
    }

    @Test
    void testContainsKey() {
        HashMap map = new HashMap();
        map.put("hello", "world");

        assertTrue(map.containsKey("hello"));
        assertFalse(map.containsKey("bye"));
    }

    @Test
    void testRemove() {
        HashMap map = new HashMap();
        map.put("x", 10);
        map.put("y", 20);

        assertEquals(10, map.remove("x"));
        assertNull(map.get("x"));
        assertEquals(1, map.size());
    }

    @Test
    void testIsEmptyAndSize() {
        HashMap map = new HashMap();

        assertTrue(map.isEmpty());
        map.put("item", "value");
        assertFalse(map.isEmpty());
        assertEquals(1, map.size());
    }

    @Test
    void testGetKeys() {
        HashMap map = new HashMap();
        map.put("a", 1);
        map.put("b", 2);

        Object[] keys = map.getKeys();
        assertEquals(2, keys.length);
        assertTrue(Arrays.asList(keys).contains("a"));
        assertTrue(Arrays.asList(keys).contains("b"));
    }

    @Test
    void testGetValues() {
        HashMap map = new HashMap();
        map.put("k1", "v1");
        map.put("k2", "v2");

        Object[] values = map.getValues();
        assertEquals(2, values.length);
        assertTrue(Arrays.asList(values).contains("v1"));
        assertTrue(Arrays.asList(values).contains("v2"));
    }

    @Test
    void testValidateKey_NullKey() {
        HashMap map = new HashMap();
        assertThrows(IllegalArgumentException.class, () -> map.put(null, "value"));
        assertThrows(IllegalArgumentException.class, () -> map.get(null));
        assertThrows(IllegalArgumentException.class, () -> map.remove(null));
    }
}
