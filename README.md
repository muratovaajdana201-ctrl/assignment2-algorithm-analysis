# Assignment 2 - Algorithm Analysis

## Introduction

This assignment implements and compares three data structures:

- DynamicArray
- LinkedList
- MinHeap

The project includes implementations, tests, benchmark workloads, result
tables, charts, complexity analysis and loop invariant proofs.

The main goal is to compare theoretical algorithmic complexity with
measured performance.

## Data Structures

### DynamicArray

DynamicArray stores elements in an integer array.

The implemented operations include:

- add
- add at a specific index
- remove
- get
- contains

The internal array is resized when it becomes full.

### LinkedList

LinkedList is implemented using nodes.

The implementation keeps references to both the head and tail nodes.

The implemented operations include:

- add
- add at a specific index
- remove
- get
- contains

### MinHeap

MinHeap is implemented using an array and maintains the min-heap property.

The implemented operations include:

- insert
- peekMin
- extractMin

The heap uses siftUp after insertion and siftDown after extraction.

## Testing

Tests.java checks the main functionality of all three data structures.

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

The final test output was:

Testing DynamicArray...
DynamicArray: PASSED
Testing LinkedList...
LinkedList: PASSED
Testing MinHeap...
MinHeap: PASSED

ALL TESTS PASSED.

## Benchmark

Benchmark.java contains four workloads.

### Workload 1 - Random Access

Random indexes are used to compare indexed access between DynamicArray
and LinkedList.

The benchmark records execution time and the number of accesses.

### Workload 2 - Search

Linear search is performed on DynamicArray and LinkedList.

The benchmark records execution time and the number of comparisons.

### Workload 3 - Insertion and Removal

Insertion and removal are measured at the beginning and in the middle
of DynamicArray and LinkedList.

The benchmark records execution time and access metrics.

### Workload 4 - Priority Processing

MinHeap insertion and extraction are measured for different input sizes.

The benchmark records execution time and comparison counts.

The benchmark uses the following input sizes:

- 100
- 1,000
- 10,000
- 100,000

## Results

The benchmark tables are stored in:

results/tables/

The available result files are:

- workload1_random_access.csv
- workload2_search.csv
- workload3_insertion_removal.csv
- workload4_priority_processing.csv
- Assignment2_Results.xlsx

Charts for the benchmark workloads are stored in:

results/plots/

The benchmark results show that DynamicArray provides direct indexed
access, while LinkedList must traverse nodes to reach an indexed position.

For beginning insertion and removal, LinkedList can update the head
without shifting all existing elements.

For middle operations, DynamicArray can access the required index directly
but may need to move elements. LinkedList must traverse the list before
performing the operation.

The MinHeap results show the behaviour expected from a binary heap.
Insertion and extraction depend on the height of the heap.

## Complexity Analysis

The theoretical complexity analysis is stored in:

results/tables/complexity_analysis.md

It contains best-case, average-case, worst-case and auxiliary-space
complexities for the implemented operations.

## Loop Invariants

The loop invariant proofs are stored in:

results/invariant_proofs.md

The proofs cover:

- DynamicArray.contains()
- MinHeap.siftDown()

## Performance Analysis

The comparison between theoretical complexity and measured benchmark
results is stored in:

results/tables/performance_analysis.md

The analysis also discusses access and comparison counters.

Measured execution time can be affected by JVM optimisation, caching,
memory layout, garbage collection and other system effects.

Therefore, the benchmark results are used to examine general performance
patterns rather than treating individual nanosecond measurements as exact
complexity proofs.

## Project Structure

assignment2-algorithm-analysis/
- README.md
- pom.xml
- src/
    - main/
        - java/
            - DynamicArray.java
            - LinkedList.java
            - MinHeap.java
            - Benchmark.java
    - test/
        - java/
            - Tests.java
- results/
    - plots/
        - workload1_random_access.png
        - workload2_search.png
        - workload3_insertion_removal.png
        - workload4_priority_processing.png
    - tables/
        - Assignment2_Results.xlsx
        - workload1_random_access.csv
        - workload2_search.csv
        - workload3_insertion_removal.csv
        - workload4_priority_processing.csv
        - complexity_analysis.md
        - performance_analysis.md
    - invariant_proofs.md

## How to Run

Run Tests.java first to check that the data structures work correctly.

The expected result is:

ALL TESTS PASSED.

Then run Benchmark.java to generate the benchmark measurements.

The benchmark results can then be recorded in the CSV and Excel tables
and used to create the charts.

## Final Result

All three data structures passed the functional tests.

The project contains the implementations, tests, benchmark workloads,
benchmark tables, charts, complexity analysis, performance analysis and
loop invariant proofs.

## Conclusion

The project demonstrates the relationship between data structure design,
algorithmic complexity and practical execution performance.

The benchmark measurements provide experimental results that can be
compared with the theoretical complexity of the implemented operations.