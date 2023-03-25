package org.dsa.sorting.quick;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuickSortTest {

    private static final Logger log = Logger.getLogger(QuickSortTest.class.getName());

    @Test
    void sort() {
        QuickSort<Integer> quickSort = new QuickSort<>();
        List<Integer> inputList = List.of(8, 78, 53, 568, 2, 566);
        log.info(inputList.toString());
        List<Integer> sortedList = quickSort.sort(inputList, Integer::compare);
        log.info(sortedList.toString());
        assertEquals(inputList.stream().sorted().toList(), sortedList);
    }
}