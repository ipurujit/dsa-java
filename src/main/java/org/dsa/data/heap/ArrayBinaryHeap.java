package org.dsa.data.heap;

import org.dsa.util.SwapUtil;

import java.util.*;

/**
 * Min heap based implementation
 * @param <T> - Which object heap stores
 */
public class ArrayBinaryHeap<T> implements CustomHeap<T> {
    private final Comparator<T> comparator;
    private final List<T> heap;

    public ArrayBinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<>();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return heap.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return heap.iterator();
    }

    @Override
    public Object[] toArray() {
        return heap.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return heap.toArray(a);
    }

    @Override
    public boolean add(T t) {
        return addOp(t);
    }

    @Override
    public boolean offer(T t) {
        return addOp(t);
    }

    @Override
    public T remove() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty!");
        }
        return removeOp();
    }

    @Override
    public T poll() {
        return heap.isEmpty() ? null : removeOp();
    }

    @Override
    public T element() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty!");
        }
        return heap.get(0);
    }

    @Override
    public T peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object t : c) {
            if (!contains(t)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T t : c) {
            if (!addOp(t)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        heap.clear();
    }

    private T removeOp() {
        // swap root and end
        SwapUtil.swap(heap, 0, heap.size() - 1);
        // remove root (at the end)
        T removed = heap.remove(heap.size() - 1);
        // heapify the rest (keep swapping from root till leaf until heapified)
        heapifyAfterRemove(0);
        return removed;
    }

    private void heapifyAfterRemove(int idx) {
        // get left and right
        int left = 2 * idx + 1;
        int right = left + 1;
        int smallestIdx = idx;
        if (left < heap.size() && comparator.compare(heap.get(smallestIdx), heap.get(left)) > 0) {
            smallestIdx = left;
        }
        if (right < heap.size() && comparator.compare(heap.get(smallestIdx), heap.get(right)) > 0) {
            smallestIdx = right;
        }
        if (smallestIdx != idx) {
            // swap smallest with idx
            SwapUtil.swap(heap, idx, smallestIdx);
            heapifyAfterRemove(smallestIdx);
        }
    }

    private boolean addOp(T t) {
        boolean addResult = heap.add(t);
        // heapify (keep swapping from leaf till root until heapified)
        heapifyAfterAdd();
        return addResult;
    }

    private void heapifyAfterAdd() {
        int idx = heap.size() - 1;
        int parentIdx = (idx - 1) / 2;
        while (idx > 0 &&
                comparator.compare(heap.get(parentIdx), heap.get(idx)) > 0) {
            SwapUtil.swap(heap, parentIdx, idx);
            idx = parentIdx;
            parentIdx = (idx - 1) / 2;
        }
    }
}
