package org.dsa.sorting.merge;

import org.dsa.sorting.IBaseSort;

import java.util.*;
import java.util.logging.Logger;

public class MergeSort<T> implements IBaseSort<T> {
    private static final Logger log = Logger.getLogger(MergeSort.class.getName());

    private void merge(List<T> listToSort, Comparator<T> comparator, int start, int mid, int end) {
        // copy values from original list to separate queue and update original list
        Queue<T> leftSubList = new LinkedList<>(listToSort.subList(start, mid + 1));
        Queue<T> rightSubList = new LinkedList<>(listToSort.subList(mid + 1, end + 1));
        int idx = start;
        while (!leftSubList.isEmpty() && !rightSubList.isEmpty()) {
            if (comparator.compare(leftSubList.peek(), rightSubList.peek()) < 0) {
                listToSort.set(idx++, leftSubList.poll());
            } else {
                listToSort.set(idx++, rightSubList.poll());
            }
        }
        while (!leftSubList.isEmpty()) {
            listToSort.set(idx++, leftSubList.poll());
        }
        while (!rightSubList.isEmpty()) {
            listToSort.set(idx++, rightSubList.poll());
        }
    }

    private void mergeSort(List<T> listToSort, Comparator<T> comparator, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(listToSort, comparator, start, mid);
            mergeSort(listToSort, comparator, mid + 1, end);
            merge(listToSort, comparator, start, mid, end);
        }
    }

    public List<T> sort(List<T> input, Comparator<T> comparator) {
        log.entering(this.getClass().getName(), "sort");
        int len = input.size();
        // clone input
        List<T> listToSort = new ArrayList<>(input);
        mergeSort(listToSort, comparator, 0, len - 1);
        log.exiting(this.getClass().getName(), "sort");
        return listToSort;
    }
}
