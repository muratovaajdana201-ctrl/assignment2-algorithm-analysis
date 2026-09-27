# Loop Invariant Proofs

## 1. DynamicArray contains()

### Algorithm

The contains(x) method scans the array from left to right and returns
true when it finds the target value.

### Loop invariant

Before every iteration of the loop at index i:

> The target value x does not occur in any array position from 0
> through i - 1.

### Initialization

Before the first iteration, i = 0.

There are no positions before index 0, so the statement is true.

Therefore, the invariant holds before the loop starts.

### Maintenance

Assume the invariant is true at the beginning of an iteration.

The algorithm checks data[i].

There are two cases:

1. data[i] == x

   The method immediately returns true, so the target has been found.

2. data[i] != x

   The algorithm moves to the next index, i + 1.

   Since the current position i also does not contain x, all positions
   from 0 through i do not contain x.

   Therefore, the invariant remains true for the next iteration.

### Termination

The loop terminates when either:

- the target is found and the method returns true, or
- i == size, meaning every element has been checked.

If the loop finishes without finding x, the invariant tells us that
no position from 0 through size - 1 contains x.

Therefore returning false is correct.

### Conclusion

The contains()method correctly determines whether the target value
exists in the DynamicArray.



## 2. MinHeap siftDown()

### Algorithm

After removing the minimum element, the last element is moved to the root.
siftDown() repeatedly swaps this element with the smaller child until
the min-heap property is restored.

### Loop invariant

At the beginning of every iteration of siftDown():

> Every subtree below the current node satisfies the min-heap property,
> except possibly at the current node.

### Initialization

Initially, current is the root.

Before the first iteration, all subtrees below the root were already valid
min-heaps because the heap was valid before the extraction.

The only possible violation is at the new root.

Therefore the invariant holds initially.

### Maintenance

Assume the invariant is true at the beginning of an iteration.

The algorithm compares the current node with its left and right children
and finds the smallest value.

There are two cases:

1. The current node is already the smallest.

   No swap is necessary. The current node and its children satisfy the
   min-heap property, so the algorithm terminates.

2. One child is smaller than the current node.

   The algorithm swaps the current node with the smaller child.

   The node moved upward is now no larger than either child.

   The only possible remaining violation moves down to the child's old
   position.

   Therefore the invariant is preserved with the new current position.

### Termination

The loop terminates when the current node is no larger than its children,
or when it reaches a leaf.

At this point there is no remaining heap-property violation.

Therefore the complete structure satisfies the min-heap property.

### Conclusion

siftDown() correctly restores the min-heap property after extractMin().