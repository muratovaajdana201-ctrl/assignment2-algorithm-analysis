# Complexity Analysis

## DynamicArray

| Operation | Best | Average | Worst | Auxiliary Space |
|---|---|---|---|---|
| add(x) | Θ(1) | Θ(1) amortized | Θ(n) | Θ(n) |
| add(i, x) | Θ(1) | Θ(n) | Θ(n) | Θ(n) |
| remove(i) | Θ(1) | Θ(n) | Θ(n) | Θ(1) |
| get(i) | Θ(1) | Θ(1) | Θ(1) | Θ(1) |
| contains(x) | Θ(1) | Θ(n) | Θ(n) | Θ(1) |

### DynamicArray explanation

`get(i)` is Θ(1) because the array provides direct indexing.

`contains(x)` is Θ(1) in the best case when the value is found at the first position, and Θ(n) in the average and worst cases because elements may need to be checked sequentially.

`add(x)` is Θ(1) amortized because elements are normally appended directly. When the internal array becomes full, resizing requires copying all existing elements, which takes Θ(n).

`add(i, x)` may require shifting elements to the right, so the worst case is Θ(n).

`remove(i)` may require shifting elements to the left, so the worst case is Θ(n).

---

## LinkedList

| Operation | Best | Average | Worst | Auxiliary Space |
|---|---|---|---|---|
| add(x) | Θ(1) | Θ(1) | Θ(1) | Θ(1) |
| add(i, x) | Θ(1) | Θ(n) | Θ(n) | Θ(1) |
| remove(i) | Θ(1) | Θ(n) | Θ(n) | Θ(1) |
| get(i) | Θ(1) | Θ(n) | Θ(n) | Θ(1) |
| contains(x) | Θ(1) | Θ(n) | Θ(n) | Θ(1) |

### LinkedList explanation

`add(x)` is Θ(1) because the implementation maintains a `tail` pointer.

`get(i)` requires traversing the list from the head, so it is Θ(n) in the average and worst cases.

`contains(x)` may need to inspect every node, giving Θ(n) in the worst case.

`add(i, x)` requires reaching the insertion position, so it is Θ(n) in the average and worst cases.

`remove(i)` also requires reaching the previous node, so it is Θ(n) in the average and worst cases.

---

## MinHeap

| Operation | Best | Average | Worst | Auxiliary Space |
|---|---|---|---|---|
| insert(x) | Θ(1) | Θ(log n) | Θ(log n) | Θ(1) |
| peekMin() | Θ(1) | Θ(1) | Θ(1) | Θ(1) |
| extractMin() | Θ(1) | Θ(log n) | Θ(log n) | Θ(1) |

### MinHeap explanation

`peekMin()` is Θ(1) because the minimum element is stored at the root.

`insert(x)` places the new element at the end and then performs `siftUp`. The heap height is Θ(log n), so the worst case is Θ(log n).

`extractMin()` removes the root, moves the last element to the root, and performs `siftDown`. Since the height of the heap is Θ(log n), the worst case is Θ(log n).

The heap uses an array, so the auxiliary space used by an individual operation is Θ(1), excluding the storage required for the heap itself.