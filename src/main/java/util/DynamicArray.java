package util;

import java.util.Iterator;

/**
 * Day 2: Implemented core data structure - DynamicArray
 * A dynamic array implementation that automatically resizes when needed.
 * This class provides similar functionality to ArrayList but with a custom implementation.
 *
 * @param <T> the type of elements in this array
 */
public class DynamicArray<T> implements Iterable<T> {

    private T[] arr;
    private int size = 0;
    private int capacity = 0;

    /**
     * Constructs an empty DynamicArray with an initial capacity of 16.
     */
    public DynamicArray() {
        this(16);
    }

    /**
     * Constructs an empty DynamicArray with the specified initial capacity.
     *
     * @param capacity the initial capacity of the array
     * @throws IllegalArgumentException if the specified capacity is negative
     */
    public DynamicArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity cannot be negative.");
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    /**
     * Returns the number of elements in this array.
     *
     * @return the number of elements in this array
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this array contains no elements.
     *
     * @return true if this array contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the element at the specified position in this array.
     *
     * @param index index of the element to return
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T get(int index) {
        return arr[index];
    }

    /**
     * Replaces the element at the specified position in this array with the specified element.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     */
    public void set(int index, T element) {
        arr[index] = element;
    }

    /**
     * Removes all elements from this array.
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    /**
     * Appends the specified element to the end of this array.
     *
     * @param element element to be appended to this array
     */
    public void add(T element) {
        if (size == capacity) {
            resize();
        }
        arr[size++] = element;
    }

    /**
     * Doubles the capacity of this array when it is full.
     */
    private void resize() {
        capacity = capacity == 0 ? 1 : capacity * 2;
        T[] newArr = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    /**
     * Removes the element at the specified position in this array.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the array
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
        T element = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[size - 1] = null;
        size--;
        return element;
    }

    /**
     * Removes the first occurrence of the specified element from this array, if it is present.
     *
     * @param obj element to be removed from this array, if present
     * @return true if this array contained the specified element
     */
    public boolean remove(Object obj) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(obj)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if this array contains the specified element.
     *
     * @param obj element whose presence in this array is to be tested
     * @return true if this array contains the specified element
     */
    public boolean contains(Object obj) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns an iterator over the elements in this array in proper sequence.
     *
     * @return an iterator over the elements in this array in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return arr[index++];
            }
        };
    }

    /**
     * Returns a string representation of this array.
     *
     * @return a string representation of this array
     */
    @Override
    public String toString() {
        if (size == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(arr[i]).append(", ");
        }
        sb.append(arr[size - 1]).append("]");
        return sb.toString();
    }

    /**
     * Returns an array containing all of the elements in this array in proper sequence.
     *
     * @return an array containing all of the elements in this array
     */
    public Object[] getValues() {
        Object[] result = new Object[size];
        System.arraycopy(arr, 0, result, 0, size);
        return result;
    }

    public static void main(String[] args) {
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();

        dynamicArray.add(1);
        dynamicArray.add(2);
        dynamicArray.add(3);

        System.out.println("Dynamic Array: " + dynamicArray);

        System.out.println("Element at index 1: " + dynamicArray.get(1));
        dynamicArray.set(1, 5);
        System.out.println("After setting index 1 to 5: " + dynamicArray);

        dynamicArray.removeAt(0);
        System.out.println("After removing element at index 0: " + dynamicArray);

        dynamicArray.remove(Integer.valueOf(5));
        System.out.println("After removing element 5: " + dynamicArray);

        System.out.println("Size of Dynamic Array: " + dynamicArray.size());
        System.out.println("Is array empty? " + dynamicArray.isEmpty());
    }
}
