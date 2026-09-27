# Performance and Design Analysis

## 1. DynamicArray vs LinkedList

The random access benchmark shows a large difference between DynamicArray
and LinkedList.

For n = 100000:

- DynamicArray time: 20,520 ns
- LinkedList time: 1,128,401,160 ns

DynamicArray provides direct indexed access, so the number of recorded
accesses remains constant for the 10,000 random access operations.

For n = 100000:

- DynamicArray accesses: 10,000
- LinkedList accesses: 502,499,208

LinkedList needs to traverse nodes from the head to reach an indexed
position. As the input size increases, this results in a much larger
number of node accesses.

This agrees with the theoretical complexity:

- DynamicArray get(i): Θ(1)
- LinkedList get(i): Θ(n)

The measured execution times are also consistent with this difference.

## 2. Search Performance

Both DynamicArray and LinkedList use linear search.

For n = 100000, both structures performed:

- DynamicArray comparisons: 75,465,090
- LinkedList comparisons: 75,465,090

The number of comparisons is the same because both structures examine
elements sequentially.

For n = 100000:

- DynamicArray time: 64,046,540 ns
- LinkedList time: 175,173,220 ns

Both structures therefore show linear search behaviour.

The difference in measured execution time is related to the way elements
are accessed in the two structures.

The theoretical complexity of contains(x) is:

- Best case: Θ(1)
- Average case: Θ(n)
- Worst case: Θ(n)

## 3. Insertion and Removal

The benchmark measures insertion and removal at the beginning and in the
middle of the structures.

### Beginning insertion

For n = 100000:

- DynamicArray: 16,356,540 ns
- LinkedList: 60,520 ns

DynamicArray has to shift existing elements when inserting at index 0.

LinkedList can create a new node and update the head pointer without
shifting the existing nodes.

The recorded accesses were:

- DynamicArray: 201,000,000
- LinkedList: 0

This illustrates the difference between the two implementations.

### Beginning removal

For n = 100000:

- DynamicArray: 11,038,840 ns
- LinkedList: 52,640 ns

DynamicArray shifts the remaining elements after removing the first
element.

LinkedList updates the head reference.

The recorded accesses were:

- DynamicArray: 201,000,000
- LinkedList: 1,000

### Middle insertion

For n = 100000:

- DynamicArray: 8,049,980 ns
- LinkedList: 113,391,980 ns

DynamicArray can directly reach the middle index but has to shift
elements after the insertion point.

LinkedList must traverse nodes to reach the insertion position before
performing the insertion.

The recorded accesses were:

- DynamicArray: 100,001,000
- LinkedList: 50,498,500

### Middle removal

For n = 100000:

- DynamicArray: 6,102,360 ns
- LinkedList: 112,724,000 ns

DynamicArray shifts the elements after the removed position.

LinkedList first needs to traverse the list to reach the required
position.

The recorded accesses were:

- DynamicArray: 100,500,000
- LinkedList: 50,250,000

Both structures therefore have linear worst-case behaviour for middle
insertion and removal, but the practical costs are different.

## 4. Priority Processing with MinHeap

The MinHeap benchmark measures insertion and extraction for different
input sizes.

### Insert

| n | Average time (ns) | Comparisons |
|---:|---:|---:|
| 100 | 65,360 | 206 |
| 1,000 | 211,060 | 2,326 |
| 10,000 | 1,475,380 | 22,753 |
| 100,000 | 3,291,740 | 227,857 |

### Extract

| n | Average time (ns) | Comparisons |
|---:|---:|---:|
| 100 | 203,340 | 863 |
| 1,000 | 378,660 | 14,996 |
| 10,000 | 2,642,120 | 216,531 |
| 100,000 | 16,682,060 | 2,831,426 |

Insertion uses siftUp, which can move an element from the bottom of the
heap towards the root.

Extraction removes the minimum element from the root and uses siftDown
to restore the heap property.

A binary heap has logarithmic height, so an individual insertion and
extraction operation has worst-case complexity Θ(log n).

The comparison values shown in the benchmark are totals for the
operations performed for each input size, so the total number of
comparisons increases as n increases.

## 5. Scaling Behaviour

The benchmark results generally follow the expected theoretical
behaviour.

DynamicArray random access remains constant in terms of the number of
access operations because indexed access does not require traversal.

LinkedList random access requires traversal and therefore produces a much
larger number of accesses as n increases.

Search is linear for both structures because elements are checked
sequentially.

Beginning insertion and removal show the advantage of changing the head
of a linked list without shifting an array.

Middle insertion and removal show the different costs of array shifting
and linked-list traversal.

MinHeap operations grow more slowly than linear operations because the
height of the binary heap grows logarithmically.

## 6. Theory vs Measurements

The measured execution times do not increase perfectly according to the
theoretical complexity.

This is expected because Java execution is affected by factors such as:

- JVM optimisation
- memory layout
- CPU caching
- garbage collection
- operating system scheduling
- other runtime effects

Therefore, individual nanosecond measurements should not be treated as
exact proofs of algorithmic complexity.

The access and comparison counters provide additional information about
the operations performed by the implementations.

These counters help explain the differences between the data structures.

## 7. Design Conclusions

The benchmark demonstrates that the appropriate data structure depends
on the operations required by the workload.

DynamicArray provides efficient indexed access.

LinkedList can perform beginning insertion and removal without shifting
all existing elements.

DynamicArray and LinkedList both have linear worst-case behaviour for
middle insertion and removal, but they have different practical costs.

MinHeap is suitable for workloads that repeatedly require the minimum
element while maintaining the heap property.

Overall, the benchmark demonstrates the relationship between data
structure design, theoretical complexity and measured performance.
## Reproducibility

The benchmark was executed using the implemented DynamicArray, LinkedList,
and MinHeap classes. The reported results were generated from the benchmark
program and saved in the results/tables and results/plots directories.