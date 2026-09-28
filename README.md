# Assignment 2 - Algorithm Analysis
AIDANA MURATOVA SE-2539
## 1. Introduction

This assignment implements and compares three data structures:

- DynamicArray
- LinkedList
- MinHeap

The project includes data structure implementations, functional tests, benchmark workloads, result tables, charts, complexity analysis, performance analysis and loop invariant proofs.

The main goal is to compare theoretical algorithmic complexity with measured performance.

## 2. Data Structures

### 2.1 DynamicArray

DynamicArray stores elements in an integer array.

The implemented operations include:

- add
- add at a specific index
- remove
- get
- contains

The internal array is resized when it becomes full.

DynamicArray provides direct indexed access. Insertions and removals may require shifting elements.

### 2.2 LinkedList

LinkedList is implemented using nodes.

The implementation keeps references to both the head and tail nodes.

The implemented operations include:

- add
- add at a specific index
- remove
- get
- contains

LinkedList can efficiently add and remove elements at the beginning, but indexed access requires traversing the list.

### 2.3 MinHeap

MinHeap is implemented using an array and maintains the min-heap property.

The implemented operations include:

- insert
- peekMin
- extractMin

The heap uses `siftUp` after insertion and `siftDown` after extraction.

## 3. Testing

`Tests.java` checks the main functionality of all three data structures.

The tests include:

- empty structures
- single elements
- multiple elements
- duplicate values
- insertion
- removal
- boundary cases
- invalid indexes
- large inputs
- MinHeap ordering

### Test Output

The following screenshot shows the final test execution:

![Test Output](results/plots/test_output.png)

The tests passed successfully:

```text
Testing DynamicArray...
DynamicArray: PASSED
Testing LinkedList...
LinkedList: PASSED
Testing MinHeap...
MinHeap: PASSED

ALL TESTS PASSED.
```

## 4. Benchmark

`Benchmark.java` contains four workloads used to compare the performance of the implemented data structures.

The benchmark uses the following input sizes:

- 100
- 1,000
- 10,000
- 100,000

Execution time is measured using `System.nanoTime()`.

The benchmark also records additional operation metrics such as accesses and comparisons.

### Benchmark Output

The following screenshot shows the benchmark execution output:

![Benchmark Output](results/plots/benchmark_output.png)

### 4.1 Workload 1 - Random Access

![Workload 1 - Random Access](results/plots/workload1_random_access.png)

Random indexes are used to compare indexed access between DynamicArray and LinkedList.

The benchmark records:

- execution time
- number of accesses

DynamicArray provides direct indexed access, while LinkedList must traverse nodes to reach an indexed position.

### 4.2 Workload 2 - Search

![Workload 2 - Search](results/plots/workload2_search.png)

Linear search is performed on DynamicArray and LinkedList.

The benchmark records:

- execution time
- number of comparisons

Both structures use linear search, so the theoretical search complexity is Θ(n).

### 4.3 Workload 3 - Insertion and Removal

Insertion and removal are measured at the beginning and in the middle of DynamicArray and LinkedList.

The benchmark records execution time and access metrics.

Beginning operations demonstrate the advantage of LinkedList because elements can be inserted or removed by updating the head.

Middle operations involve different costs. DynamicArray can directly access the required position but may need to shift elements. LinkedList must traverse the list before performing the operation.

#### Insertion

![Workload 3 - Insertion](results/plots/workload3_insertion.png)

#### Removal

![Workload 3 - Removal](results/plots/workload3_removal.png)

### 4.4 Workload 4 - Priority Processing

![Workload 4 - Priority Processing](results/plots/workload4_priority_processing.png)

MinHeap insertion and extraction are measured for
