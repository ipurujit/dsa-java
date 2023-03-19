package org.dsa.sorting.insertion;

import org.dsa.sorting.IBaseSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InsertionSort<T> implements IBaseSort<T> {


    public List<T> sort(List<T> input, Comparator<T> comparator) {
        int len = input.size();
        // clone input
        List<T> listToSort = new ArrayList<>(input);
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
        return listToSort;
    }
}
