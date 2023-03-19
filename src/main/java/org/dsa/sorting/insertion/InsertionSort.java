package org.dsa.sorting.insertion;

import org.dsa.sorting.IBaseSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class InsertionSort<T> implements IBaseSort<T> {
    private static final Logger log = Logger.getLogger(InsertionSort.class.getName());
    // This private sorts inplace
    private void insertSort(List<T> listToSort, Comparator<T> comparator, int len) {
        // iterate from 2nd elem up to last elem
        for (int idx = 1; idx < len; idx++) {
            T currElem = listToSort.get(idx);
            int currIdx = idx - 1;
            while (currIdx >= 0 &&
                    comparator.compare(currElem, listToSort.get(currIdx)) < 0) {
                // while currElem is smaller, keep moving prev index by one index ahead
                listToSort.set(currIdx + 1, listToSort.get(currIdx));
                currIdx--;
            }
            listToSort.set(currIdx + 1, currElem);
        }
    }

    public List<T> sort(List<T> input, Comparator<T> comparator) {
        log.entering(this.getClass().getName(), "sort");
        int len = input.size();
        // clone input
        List<T> listToSort = new ArrayList<>(input);
        insertSort(listToSort, comparator, len);
        log.entering(this.getClass().getName(), "sort");
        return listToSort;
    }
}
