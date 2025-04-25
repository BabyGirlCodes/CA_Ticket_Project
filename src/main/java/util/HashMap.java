package util;

/**
 * Day 3: Implemented HashMap for efficient key-value storage
 * A custom implementation of a hash map data structure.
 * This class provides O(1) average case complexity for insertions and lookups.
 *
 * @param <K> the type of keys
 * @param <V> the type of values
 */
public class HashMap<K, V> {
    private static final int INITIAL_SIZE = 103;
    private DynamicArray<Entry<K, V>>[] map;
    private int count;

    /**
     * Constructs a new HashMap with default initial size.
     */
    public HashMap() {
        map = new DynamicArray[INITIAL_SIZE];
        count = 0;
    }

    /**
     * Inserts or updates a key-value pair into the map.
     *
     * @param key the key
     * @param value the value
     * @return the previous value if key existed, otherwise null
     */
    public V put(K key, V value) {
        validateKey(key);

        int destinationSlot = calculateSlot(key);

        if (map[destinationSlot] == null) {
            map[destinationSlot] = new DynamicArray<>();
        }

        DynamicArray<Entry<K, V>> slotList = map[destinationSlot];
        for (int i = 0; i < slotList.size(); i++) {
            Entry<K, V> currentEntry = slotList.get(i);
            if (currentEntry.key.equals(key)) {
                V oldValue = currentEntry.value;
                currentEntry.value = value;
                return oldValue;
            }
        }

        slotList.add(new Entry<>(key, value));
        count++;
        return null;
    }

    /**
     * Retrieves the value associated with a given key.
     *
     * @param key the key
     * @return the value if found, otherwise null
     */
    public V get(K key) {
        validateKey(key);

        int destinationSlot = calculateSlot(key);

        if (map[destinationSlot] == null) return null;

        DynamicArray<Entry<K, V>> slotList = map[destinationSlot];
        for (int i = 0; i < slotList.size(); i++) {
            Entry<K, V> currentEntry = slotList.get(i);
            if (currentEntry.key.equals(key)) {
                return currentEntry.value;
            }
        }

        return null;
    }

    /**
     * Checks if the map contains the given key.
     *
     * @param key the key
     * @return true if the key exists, otherwise false
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Removes the entry for the given key from the map.
     *
     * @param key the key
     * @return the removed value if key existed, otherwise null
     */
    public V remove(K key) {
        validateKey(key);
        int destinationSlot = calculateSlot(key);

        DynamicArray<Entry<K, V>> slotList = map[destinationSlot];
        if (slotList == null) return null;

        for (int i = 0; i < slotList.size(); i++) {
            Entry<K, V> currentEntry = slotList.get(i);
            if (currentEntry.key.equals(key)) {
                V oldValue = currentEntry.value;
                slotList.removeAt(i);
                count--;
                return oldValue;
            }
        }

        return null;
    }

    /**
     * Returns an array of all keys in the map.
     *
     * @return an array of keys
     */
    public K[] getKeys() {
        K[] keyArray = (K[]) new Object[count];
        int posTracker = 0;

        for (int i = 0; i < map.length; i++) {
            if (map[i] != null) {
                DynamicArray<Entry<K, V>> slotList = map[i];
                for (int j = 0; j < slotList.size(); j++) {
                    keyArray[posTracker++] = slotList.get(j).key;
                }
            }
        }

        return keyArray;
    }

    /**
     * Returns an array of all values in the map.
     *
     * @return an array of values
     */
    public V[] getValues() {
        V[] valueArray = (V[]) new Object[count];
        int posTracker = 0;

        for (int i = 0; i < map.length; i++) {
            if (map[i] != null) {
                DynamicArray<Entry<K, V>> slotList = map[i];
                for (int j = 0; j < slotList.size(); j++) {
                    valueArray[posTracker++] = slotList.get(j).value;
                }
            }
        }

        return valueArray;
    }

    /**
     * Returns the number of key-value pairs in the map.
     *
     * @return the size of the map
     */
    public int size() {
        return count;
    }

    /**
     * Checks if the map is empty.
     *
     * @return true if the map is empty, otherwise false
     */
    public boolean isEmpty() {
        return count == 0;
    }

    private void validateKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
    }

    private int calculateSlot(K key) {
        int hashCode = key.hashCode();
        hashCode = Math.abs(hashCode);
        return hashCode % map.length;
    }

    /**
     * A key-value pair stored in the map.
     *
     * @param <K> the type of keys
     * @param <V> the type of values
     */
    private static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
