public class Tests {

    public static void main(String[] args) {

        testDynamicArray();
        testLinkedList();
        testMinHeap();

        System.out.println();
        System.out.println("ALL TESTS PASSED.");
    }

    private static void testDynamicArray() {

        System.out.println("Testing DynamicArray...");

        DynamicArray array = new DynamicArray();

        assert array.size() == 0;


        array.add(10);
        assert array.size() == 1;
        assert array.get(0) == 10;


        array.add(20);
        array.add(30);

        assert array.size() == 3;
        assert array.get(1) == 20;


        array.add(0, 5);

        assert array.get(0) == 5;
        assert array.get(1) == 10;
        assert array.get(2) == 20;
        assert array.get(3) == 30;


        array.add(2, 15);

        assert array.get(2) == 15;


        array.add(15);
        assert array.contains(15);


        int removed = array.remove(2);

        assert removed == 15;
        assert array.get(2) == 20;


        array.remove(0);
        assert array.get(0) == 10;


        array.remove(array.size() - 1);


        DynamicArray large = new DynamicArray();

        for (int i = 0; i < 100_000; i++) {
            large.add(i);
        }

        assert large.size() == 100_000;
        assert large.get(99_999) == 99_999;


        boolean exceptionThrown = false;

        try {
            array.get(-1);
        } catch (IndexOutOfBoundsException e) {
            exceptionThrown = true;
        }

        assert exceptionThrown;

        exceptionThrown = false;

        try {
            array.get(array.size());
        } catch (IndexOutOfBoundsException e) {
            exceptionThrown = true;
        }

        assert exceptionThrown;

        System.out.println("DynamicArray: PASSED");
    }

    private static void testLinkedList() {

        System.out.println("Testing LinkedList...");

        LinkedList list = new LinkedList();


        assert list.size() == 0;


        list.add(10);

        assert list.size() == 1;
        assert list.get(0) == 10;


        list.add(20);
        list.add(30);

        assert list.size() == 3;


        list.add(0, 5);

        assert list.get(0) == 5;
        assert list.get(1) == 10;

        list.add(2, 15);

        assert list.get(2) == 15;


        list.add(list.size(), 40);

        assert list.get(list.size() - 1) == 40;


        list.add(20);
        assert list.contains(20);


        assert list.remove(0) == 5;

        assert list.remove(1) == 15;

        int lastIndex = list.size() - 1;
        list.remove(lastIndex);


        LinkedList large = new LinkedList();

        for (int i = 0; i < 100_000; i++) {
            large.add(i);
        }

        assert large.size() == 100_000;
        assert large.get(99_999) == 99_999;


        boolean exceptionThrown = false;

        try {
            list.get(-1);
        } catch (IndexOutOfBoundsException e) {
            exceptionThrown = true;
        }

        assert exceptionThrown;

        exceptionThrown = false;

        try {
            list.get(list.size());
        } catch (IndexOutOfBoundsException e) {
            exceptionThrown = true;
        }

        assert exceptionThrown;

        System.out.println("LinkedList: PASSED");
    }

    private static void testMinHeap() {

        System.out.println("Testing MinHeap...");

        MinHeap heap = new MinHeap();


        assert heap.size() == 0;


        heap.insert(10);

        assert heap.peekMin() == 10;
        assert heap.isValidMinHeap();


        heap.insert(5);
        heap.insert(20);
        heap.insert(3);
        heap.insert(7);

        assert heap.peekMin() == 3;
        assert heap.isValidMinHeap();


        heap.insert(3);
        heap.insert(7);

        assert heap.isValidMinHeap();


        int previous = Integer.MIN_VALUE;

        while (heap.size() > 0) {

            int current = heap.extractMin();

            assert current >= previous;
            assert heap.isValidMinHeap();

            previous = current;
        }


        MinHeap large = new MinHeap();

        for (int i = 100_000; i >= 1; i--) {
            large.insert(i);
        }

        assert large.isValidMinHeap();

        previous = Integer.MIN_VALUE;

        while (large.size() > 0) {
            int current = large.extractMin();

            assert current >= previous;

            previous = current;
        }


        boolean exceptionThrown = false;

        try {
            heap.peekMin();
        } catch (IllegalStateException e) {
            exceptionThrown = true;
        }

        assert exceptionThrown;

        System.out.println("MinHeap: PASSED");
    }
}