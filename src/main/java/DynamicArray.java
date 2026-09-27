public class DynamicArray {

    private int[] data;
    private int size;

    // Benchmark metrics
    private long accesses;
    private long movements;
    private long comparisons;

    public DynamicArray() {
        data = new int[10];
        size = 0;
    }

    public void add(int value) {
        if (size == data.length) {
            resize();
        }

        data[size] = value;
        accesses++;

        size++;
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        if (size == data.length) {
            resize();
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
            movements++;
            accesses += 2;
        }

        data[index] = value;
        accesses++;

        size++;
    }

    public int remove(int index) {
        checkIndex(index);

        int removedValue = data[index];
        accesses++;

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
            movements++;
            accesses += 2;
        }

        size--;

        return removedValue;
    }

    public int get(int index) {
        checkIndex(index);

        accesses++;

        return data[index];
    }

    public boolean contains(int value) {
        for (int i = 0; i < size; i++) {
            accesses++;
            comparisons++;

            if (data[i] == value) {
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    public void resetMetrics() {
        accesses = 0;
        movements = 0;
        comparisons = 0;
    }

    public long getAccesses() {
        return accesses;
    }

    public long getMovements() {
        return movements;
    }

    public long getComparisons() {
        return comparisons;
    }

    private void resize() {
        int[] newData = new int[data.length * 2];

        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
            movements++;
            accesses += 2;
        }

        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index
            );
        }
    }
}