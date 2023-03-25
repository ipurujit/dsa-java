package org.dsa.sorting.heap;

import org.dsa.data.heap.ArrayBinaryHeap;
import org.dsa.data.heap.CustomHeap;
import org.dsa.sorting.IBaseSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class HeapSort<T> implements IBaseSort<T> {
    private static final Logger log = Logger.getLogger(HeapSort.class.getName());

    private void heapSort(List<T> listToSort, Comparator<T> comparator) {
        // add to heap
        CustomHeap<T> heap = new ArrayBinaryHeap<>(comparator);
        heap.addAll(listToSort);
        listToSort.clear(); // easier to clear and add instead of set op at index
        while (!heap.isEmpty()) {
            listToSort.add(heap.remove());
        }
    }

    @Override
    public List<T> sort(List<T> input, Comparator<T> comparator) {
        log.entering(this.getClass().getName(), "sort");
        List<T> listToSort = new ArrayList<>(input);
        heapSort(listToSort, comparator);
        log.exiting(this.getClass().getName(), "sort");
        return listToSort;
    }
}
