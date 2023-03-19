package org.dsa.sorting.merge;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
    private static final Logger log = Logger.getLogger(MergeSortTest.class.getName());

    @Test
    void sort() {
        MergeSort<Integer> mergeSort = new MergeSort<>();
        List<Integer> inputList = List.of(8, 78, 53, 568, 2, 566);
        log.info(inputList.toString());
        List<Integer> sortedList = mergeSort.sort(inputList, Integer::compare);
        log.info(sortedList.toString());
        assertEquals(inputList.stream().sorted().toList(), sortedList);
    }

}