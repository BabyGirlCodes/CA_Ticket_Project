import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T> {

    private T[] arr;
    private int size = 0;
    private int capacity = 0;

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity cannot be negative.");
        this.capacity = capacity;
        arr = (T[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        return arr[index];
    }

    public void set(int index, T element) {
        arr[index] = element;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    public void add(T element) {
        if (size == capacity) {
            resize();
        }
        arr[size++] = element;
    }

    private void resize() {
        capacity = capacity == 0 ? 1 : capacity * 2;
        T[] newArr = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

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

    public boolean remove(Object obj) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(obj)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public boolean contains(Object obj) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(obj)) {
                return true;
            }
        }
        return false;
    }

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
