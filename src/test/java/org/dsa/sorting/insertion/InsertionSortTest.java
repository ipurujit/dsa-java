package org.dsa.sorting.insertion;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsertionSortTest {
    private static final Logger log = Logger.getLogger(InsertionSortTest.class.getName());

    @Test
    void sort() {
        InsertionSort<Integer> insertionSort = new InsertionSort<>();
        List<Integer> inputList = List.of(8, 78, 53, 568, 2, 566);
        log.info(inputList.toString());
        List<Integer> sortedList = insertionSort.sort(inputList, Integer::compare);
        log.info(sortedList.toString());
        assertEquals(inputList.stream().sorted().toList(), sortedList);
    }
}