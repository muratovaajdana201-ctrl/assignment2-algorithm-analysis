public class LinkedList {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    // Benchmark metrics
    private long accesses;
    private long movements;
    private long comparisons;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index
            );
        }

        Node newNode = new Node(value);

        // Insert at beginning
        if (index == 0) {
            newNode.next = head;
            head = newNode;

            if (size == 0) {
                tail = newNode;
            }

            movements++;
            size++;
            return;
        }

        // Insert at end
        if (index == size) {
            tail.next = newNode;
            tail = newNode;

            movements++;
            size++;
            return;
        }

        Node previous = getNode(index - 1);

        newNode.next = previous.next;
        previous.next = newNode;

        movements += 2;
        size++;
    }

    public int remove(int index) {
        checkIndex(index);

        // Remove first node
        if (index == 0) {
            int removedValue = head.data;
            accesses++;

            head = head.next;
            movements++;

            size--;

            if (size == 0) {
                tail = null;
            }

            return removedValue;
        }

        Node previous = getNode(index - 1);
        Node removed = previous.next;

        int removedValue = removed.data;
        accesses++;

        previous.next = removed.next;
        movements++;

        if (removed == tail) {
            tail = previous;
        }

        size--;

        return removedValue;
    }

    public int get(int index) {
        checkIndex(index);

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
            accesses++;
        }

        accesses++;

        return current.data;
    }

    public boolean contains(int value) {
        Node current = head;

        while (current != null) {
            accesses++;
            comparisons++;

            if (current.data == value) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public int size() {
        return size;
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

    private Node getNode(int index) {
        checkIndex(index);

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
            accesses++;
        }

        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index
            );
        }
    }
}