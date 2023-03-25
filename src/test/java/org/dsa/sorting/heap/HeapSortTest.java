package org.dsa.sorting.heap;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeapSortTest {

    private static final Logger log = Logger.getLogger(HeapSortTest.class.getName());

    @Test
    void sort() {
        HeapSort<Integer> heapSort = new HeapSort<>();
        List<Integer> inputList = List.of(8, 78, 53, 568, 2, 566);
        log.info(inputList.toString());
        List<Integer> sortedList = heapSort.sort(inputList, Integer::compare);
        log.info(sortedList.toString());
        assertEquals(inputList.stream().sorted().toList(), sortedList);
    }
}