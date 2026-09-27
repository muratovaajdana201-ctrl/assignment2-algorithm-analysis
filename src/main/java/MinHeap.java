public class MinHeap {

    private int[] heap;
    private int size;

    // Benchmark metric
    private long comparisons;

    public MinHeap() {
        heap = new int[10];
        size = 0;
    }

    public void insert(int value) {
        if (size == heap.length) {
            resize();
        }

        heap[size] = value;

        int current = size;
        size++;

        siftUp(current);
    }

    public int peekMin() {
        if (size == 0) {
            throw new IllegalStateException(
                    "Heap is empty"
            );
        }

        return heap[0];
    }

    public int extractMin() {
        if (size == 0) {
            throw new IllegalStateException(
                    "Heap is empty"
            );
        }

        int minimum = heap[0];

        heap[0] = heap[size - 1];
        size--;

        if (size > 0) {
            siftDown(0);
        }

        return minimum;
    }

    public int size() {
        return size;
    }

    public long getComparisons() {
        return comparisons;
    }

    public void resetMetrics() {
        comparisons = 0;
    }

    public boolean isValidMinHeap() {
        for (int i = 0; i < size; i++) {

            int left = leftChild(i);
            int right = rightChild(i);

            if (left < size) {
                if (heap[i] > heap[left]) {
                    return false;
                }
            }

            if (right < size) {
                if (heap[i] > heap[right]) {
                    return false;
                }
            }
        }

        return true;
    }

    private void siftUp(int index) {
        int current = index;

        while (current > 0) {

            int parent = parent(current);

            comparisons++;

            if (heap[parent] <= heap[current]) {
                break;
            }

            swap(parent, current);

            current = parent;
        }
    }

    private void siftDown(int index) {
        int current = index;

        while (true) {

            int left = leftChild(current);
            int right = rightChild(current);

            int smallest = current;

            if (left < size) {
                comparisons++;

                if (heap[left] < heap[smallest]) {
                    smallest = left;
                }
            }

            if (right < size) {
                comparisons++;

                if (heap[right] < heap[smallest]) {
                    smallest = right;
                }
            }

            if (smallest == current) {
                break;
            }

            swap(current, smallest);

            current = smallest;
        }
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void resize() {
        int[] newHeap = new int[heap.length * 2];

        for (int i = 0; i < size; i++) {
            newHeap[i] = heap[i];
        }

        heap = newHeap;
    }
}