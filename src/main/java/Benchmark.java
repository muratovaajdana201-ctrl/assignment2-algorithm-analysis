import java.util.Random;

public class Benchmark {

    private static final int[] SIZES = {100, 1_000, 10_000, 100_000};

    private static final int RANDOM_ACCESS_OPERATIONS = 10_000;
    private static final int SEARCH_OPERATIONS = 1_000;
    private static final int INSERT_REMOVE_OPERATIONS = 1_000;

    private static final int REPETITIONS = 5;
    private static final long SEED = 42L;

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("ASSIGNMENT 2 BENCHMARKS");
        System.out.println("======================================");

        benchmarkRandomAccess();
        benchmarkSearch();
        benchmarkInsertionRemoval();
        benchmarkPriorityProcessing();

        System.out.println();
        System.out.println("BENCHMARKING COMPLETE.");
    }

    // ============================================================
    // WORKLOAD 1 - RANDOM ACCESS
    // ============================================================

    private static void benchmarkRandomAccess() {

        System.out.println();
        System.out.println("WORKLOAD 1 - RANDOM ACCESS");

        for (int n : SIZES) {

            int[] indexes = generateRandomIndexes(
                    n,
                    RANDOM_ACCESS_OPERATIONS,
                    SEED
            );

            long arrayTotalTime = 0;
            long listTotalTime = 0;

            long arrayAccesses = 0;
            long listAccesses = 0;

            for (int repetition = 0; repetition < REPETITIONS; repetition++) {

                DynamicArray array = createDynamicArray(n);
                LinkedList list = createLinkedList(n);

                array.resetMetrics();
                list.resetMetrics();

                long start = System.nanoTime();

                for (int index : indexes) {
                    array.get(index);
                }

                long end = System.nanoTime();

                arrayTotalTime += end - start;
                arrayAccesses += array.getAccesses();

                start = System.nanoTime();

                for (int index : indexes) {
                    list.get(index);
                }

                end = System.nanoTime();

                listTotalTime += end - start;
                listAccesses += list.getAccesses();
            }

            long averageArrayTime = arrayTotalTime / REPETITIONS;
            long averageListTime = listTotalTime / REPETITIONS;

            long averageArrayAccesses = arrayAccesses / REPETITIONS;
            long averageListAccesses = listAccesses / REPETITIONS;

            System.out.println();
            System.out.println("n = " + n);
            System.out.println("  DynamicArray time: " + averageArrayTime + " ns");
            System.out.println("  LinkedList time:   " + averageListTime + " ns");
            System.out.println("  DynamicArray accesses: " + averageArrayAccesses);
            System.out.println("  LinkedList accesses:   " + averageListAccesses);
        }
    }

    // ============================================================
    // WORKLOAD 2 - SEARCH
    // ============================================================

    private static void benchmarkSearch() {

        System.out.println();
        System.out.println("WORKLOAD 2 - SEARCH");

        for (int n : SIZES) {

            int[] searchValues = generateSearchValues(
                    n,
                    SEARCH_OPERATIONS,
                    SEED
            );

            long arrayTotalTime = 0;
            long listTotalTime = 0;

            long arrayComparisons = 0;
            long listComparisons = 0;

            for (int repetition = 0; repetition < REPETITIONS; repetition++) {

                DynamicArray array = createDynamicArray(n);
                LinkedList list = createLinkedList(n);

                array.resetMetrics();
                list.resetMetrics();

                long start = System.nanoTime();

                for (int value : searchValues) {
                    array.contains(value);
                }

                long end = System.nanoTime();

                arrayTotalTime += end - start;
                arrayComparisons += array.getComparisons();

                start = System.nanoTime();

                for (int value : searchValues) {
                    list.contains(value);
                }

                end = System.nanoTime();

                listTotalTime += end - start;
                listComparisons += list.getComparisons();
            }

            long averageArrayTime = arrayTotalTime / REPETITIONS;
            long averageListTime = listTotalTime / REPETITIONS;

            long averageArrayComparisons =
                    arrayComparisons / REPETITIONS;

            long averageListComparisons =
                    listComparisons / REPETITIONS;

            System.out.println();
            System.out.println("n = " + n);
            System.out.println("  DynamicArray time: " + averageArrayTime + " ns");
            System.out.println("  LinkedList time:   " + averageListTime + " ns");
            System.out.println("  DynamicArray comparisons: "
                    + averageArrayComparisons);
            System.out.println("  LinkedList comparisons:   "
                    + averageListComparisons);
        }
    }

    // ============================================================
    // WORKLOAD 3 - INSERTION AND REMOVAL
    // ============================================================

    private static void benchmarkInsertionRemoval() {

        System.out.println();
        System.out.println("WORKLOAD 3 - INSERTION AND REMOVAL");

        for (int n : SIZES) {

            benchmarkBeginningInsertion(n);
            benchmarkBeginningRemoval(n);
            benchmarkMiddleInsertion(n);
            benchmarkMiddleRemoval(n);
        }
    }

    private static void benchmarkBeginningInsertion(int n) {

        long arrayTotalTime = 0;
        long listTotalTime = 0;

        long arrayAccesses = 0;
        long listAccesses = 0;

        for (int repetition = 0; repetition < REPETITIONS; repetition++) {

            DynamicArray array = createDynamicArray(n);
            LinkedList list = createLinkedList(n);

            array.resetMetrics();
            list.resetMetrics();

            long start = System.nanoTime();

            for (int i = 0; i < INSERT_REMOVE_OPERATIONS; i++) {
                array.add(0, -i);
            }

            long end = System.nanoTime();

            arrayTotalTime += end - start;
            arrayAccesses += array.getAccesses();

            start = System.nanoTime();

            for (int i = 0; i < INSERT_REMOVE_OPERATIONS; i++) {
                list.add(0, -i);
            }

            end = System.nanoTime();

            listTotalTime += end - start;
            listAccesses += list.getAccesses();
        }

        System.out.println();
        System.out.println("n = " + n);
        System.out.println("  Beginning insertion:");
        System.out.println("    DynamicArray: "
                + (arrayTotalTime / REPETITIONS) + " ns");
        System.out.println("      Accesses: "
                + (arrayAccesses / REPETITIONS));
        System.out.println("    LinkedList: "
                + (listTotalTime / REPETITIONS) + " ns");
        System.out.println("      Accesses: "
                + (listAccesses / REPETITIONS));
    }

    private static void benchmarkBeginningRemoval(int n) {

        long arrayTotalTime = 0;
        long listTotalTime = 0;

        long arrayAccesses = 0;
        long listAccesses = 0;

        for (int repetition = 0; repetition < REPETITIONS; repetition++) {

            DynamicArray array = createDynamicArray(
                    n + INSERT_REMOVE_OPERATIONS
            );

            LinkedList list = createLinkedList(
                    n + INSERT_REMOVE_OPERATIONS
            );

            array.resetMetrics();
            list.resetMetrics();

            long start = System.nanoTime();

            for (int i = 0; i < INSERT_REMOVE_OPERATIONS; i++) {
                array.remove(0);
            }

            long end = System.nanoTime();

            arrayTotalTime += end - start;
            arrayAccesses += array.getAccesses();

            start = System.nanoTime();

            for (int i = 0; i < INSERT_REMOVE_OPERATIONS; i++) {
                list.remove(0);
            }

            end = System.nanoTime();

            listTotalTime += end - start;
            listAccesses += list.getAccesses();
        }

        System.out.println("  Beginning removal:");
        System.out.println("    DynamicArray: "
                + (arrayTotalTime / REPETITIONS) + " ns");
        System.out.println("      Accesses: "
                + (arrayAccesses / REPETITIONS));
        System.out.println("    LinkedList: "
                + (listTotalTime / REPETITIONS) + " ns");
        System.out.println("      Accesses: "
                + (listAccesses / REPETITIONS));
    }

    private static void benchmarkMiddleInsertion(int n) {

        long arrayTotalTime = 0;
        long listTotalTime = 0;

        long arrayAccesses = 0;
        long listAccesses = 0;

        for (int repetition = 0; repetition < REPETITIONS; repetition++) {

            DynamicArray array = createDynamicArray(n);
            LinkedList list = createLinkedList(n);

            array.resetMetrics();
            list.resetMetrics();

            long start = System.nanoTime();

            for (int i = 0; i < INSERT_REMOVE_OPERATIONS; i++) {

                int index = Math.min(
                        n / 2 + i,
                        array.size()
                );

                array.add(index, -i);
            }

            long end = System.nanoTime();

            arrayTotalTime += end - start;
            arrayAccesses += array.getAccesses();

            start = System.nanoTime();

            for (int i = 0; i < INSERT_REMOVE_OPERATIONS; i++) {

                int index = Math.min(
                        n / 2 + i,
                        list.size()
                );

                list.add(index, -i);
            }

            end = System.nanoTime();

            listTotalTime += end - start;
            listAccesses += list.getAccesses();
        }

        System.out.println("  Middle insertion:");
        System.out.println("    DynamicArray: "
                + (arrayTotalTime / REPETITIONS) + " ns");
        System.out.println("      Accesses: "
                + (arrayAccesses / REPETITIONS));
        System.out.println("    LinkedList: "
                + (listTotalTime / REPETITIONS) + " ns");
        System.out.println("      Accesses: "
                + (listAccesses / REPETITIONS));
    }

    private static void benchmarkMiddleRemoval(int n) {

        long arrayTotalTime = 0;
        long listTotalTime = 0;

        long arrayAccesses = 0;
        long listAccesses = 0;

        for (int repetition = 0; repetition < REPETITIONS; repetition++) {

            DynamicArray array = createDynamicArray(
                    n + INSERT_REMOVE_OPERATIONS
            );

            LinkedList list = createLinkedList(
                    n + INSERT_REMOVE_OPERATIONS
            );

            array.resetMetrics();
            list.resetMetrics();

            long start = System.nanoTime();

            for (int i = 0; i < INSERT_REMOVE_OPERATIONS; i++) {

                int index = array.size() / 2;

                array.remove(index);
            }

            long end = System.nanoTime();

            arrayTotalTime += end - start;
            arrayAccesses += array.getAccesses();

            start = System.nanoTime();

            for (int i = 0; i < INSERT_REMOVE_OPERATIONS; i++) {

                int index = list.size() / 2;

                list.remove(index);
            }

            end = System.nanoTime();

            listTotalTime += end - start;
            listAccesses += list.getAccesses();
        }

        System.out.println("  Middle removal:");
        System.out.println("    DynamicArray: "
                + (arrayTotalTime / REPETITIONS) + " ns");
        System.out.println("      Accesses: "
                + (arrayAccesses / REPETITIONS));
        System.out.println("    LinkedList: "
                + (listTotalTime / REPETITIONS) + " ns");
        System.out.println("      Accesses: "
                + (listAccesses / REPETITIONS));
    }

    // ============================================================
    // WORKLOAD 4 - PRIORITY PROCESSING
    // ============================================================

    private static void benchmarkPriorityProcessing() {

        System.out.println();
        System.out.println("WORKLOAD 4 - PRIORITY PROCESSING");

        for (int n : SIZES) {

            int[] values = generateValues(n, SEED);

            long insertTotalTime = 0;
            long extractTotalTime = 0;

            long insertComparisons = 0;
            long extractComparisons = 0;

            for (int repetition = 0; repetition < REPETITIONS; repetition++) {

                MinHeap heap = new MinHeap();

                heap.resetMetrics();

                long start = System.nanoTime();

                for (int value : values) {
                    heap.insert(value);
                }

                long end = System.nanoTime();

                insertTotalTime += end - start;
                insertComparisons += heap.getComparisons();

                heap.resetMetrics();

                start = System.nanoTime();

                while (heap.size() > 0) {
                    heap.extractMin();
                }

                end = System.nanoTime();

                extractTotalTime += end - start;
                extractComparisons += heap.getComparisons();
            }

            long averageInsertTime =
                    insertTotalTime / REPETITIONS;

            long averageExtractTime =
                    extractTotalTime / REPETITIONS;

            long averageInsertComparisons =
                    insertComparisons / REPETITIONS;

            long averageExtractComparisons =
                    extractComparisons / REPETITIONS;

            System.out.println();
            System.out.println("n = " + n);
            System.out.println("  Insert average time: "
                    + averageInsertTime + " ns");
            System.out.println("  Insert comparisons: "
                    + averageInsertComparisons);
            System.out.println("  Extract average time: "
                    + averageExtractTime + " ns");
            System.out.println("  Extract comparisons: "
                    + averageExtractComparisons);
        }
    }

    // ============================================================
    // DATA GENERATION
    // ============================================================

    private static DynamicArray createDynamicArray(int n) {

        DynamicArray array = new DynamicArray();

        for (int i = 0; i < n; i++) {
            array.add(i);
        }

        return array;
    }

    private static LinkedList createLinkedList(int n) {

        LinkedList list = new LinkedList();

        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        return list;
    }

    private static int[] generateRandomIndexes(
            int n,
            int count,
            long seed
    ) {

        Random random = new Random(seed);
        int[] indexes = new int[count];

        for (int i = 0; i < count; i++) {
            indexes[i] = random.nextInt(n);
        }

        return indexes;
    }

    private static int[] generateSearchValues(
            int n,
            int count,
            long seed
    ) {

        Random random = new Random(seed + 1);
        int[] values = new int[count];

        for (int i = 0; i < count; i++) {

            if (i % 2 == 0) {
                values[i] = random.nextInt(n);
            } else {
                values[i] = n + random.nextInt(n);
            }
        }

        return values;
    }

    private static int[] generateValues(
            int n,
            long seed
    ) {

        Random random = new Random(seed);
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = random.nextInt();
        }

        return values;
    }
}