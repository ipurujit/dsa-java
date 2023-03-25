package org.dsa.sorting.bubble;

import org.dsa.sorting.IBaseSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class BubbleSort<T> implements IBaseSort<T> {
    private static final Logger log = Logger.getLogger(BubbleSort.class.getName());

    private void bubbleSort(List<T> listToSort, Comparator<T> comparator) {
        int len = listToSort.size();
        for (int idx = 0; idx < len - 1; idx++) {
            for (int innerIdx = idx+1; innerIdx < len; innerIdx++) {
                if (comparator.compare(listToSort.get(idx), listToSort.get(innerIdx)) > 0) {
                    // swap
                    T temp = listToSort.get(idx);
                    listToSort.set(idx, listToSort.get(innerIdx));
                    listToSort.set(innerIdx, temp);
                }
            }
        }
    }


    @Override
    public List<T> sort(List<T> input, Comparator<T> comparator) {
        log.entering(this.getClass().getName(), "sort");
        List<T> listToSort = new ArrayList<>(input);
        bubbleSort(listToSort, comparator);
        log.exiting(this.getClass().getName(), "sort");
        return listToSort;
    }
}
