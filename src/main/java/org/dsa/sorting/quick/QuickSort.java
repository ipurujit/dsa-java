package org.dsa.sorting.quick;

import org.dsa.sorting.IBaseSort;
import org.dsa.util.SwapUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class QuickSort<T> implements IBaseSort<T> {
    private static final Logger log = Logger.getLogger(QuickSort.class.getName());

    private int partition(List<T> listToSort, Comparator<T> comparator, int start, int end) {
        final int pivotIdx = end;
        T pivot = listToSort.get(pivotIdx);
        int idxToPutSmallerItem = start;
        for (int seekerIdx = idxToPutSmallerItem; seekerIdx < pivotIdx; seekerIdx++) {
            if (comparator.compare(pivot, listToSort.get(seekerIdx)) >= 0) {
                SwapUtil.swap(listToSort, seekerIdx, idxToPutSmallerItem);
                idxToPutSmallerItem++;
            }
        }
        SwapUtil.swap(listToSort, pivotIdx, idxToPutSmallerItem);
        return idxToPutSmallerItem;
    }

    private void quickSort(List<T> listToSort, Comparator<T> comparator, int start, int end) {
        if (start < end) {
            int mid = partition(listToSort, comparator, start, end);
            quickSort(listToSort, comparator, start, mid - 1);
            quickSort(listToSort, comparator,  mid + 1, end);
        }
    }

    @Override
    public List<T> sort(List<T> input, Comparator<T> comparator) {
        log.entering(this.getClass().getName(), "sort");
        int len = input.size();
        // clone input
        List<T> listToSort = new ArrayList<>(input);
        quickSort(listToSort, comparator, 0, len - 1);
        log.exiting(this.getClass().getName(), "sort");
        return listToSort;
    }
}
