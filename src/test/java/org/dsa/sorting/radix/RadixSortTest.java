package org.dsa.sorting.radix;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RadixSortTest {

    private static final Logger log = Logger.getLogger(RadixSortTest.class.getName());

    @Test
    void sort() {
        RadixSort<Integer> radixSort = new RadixSort<>();
        List<Integer> inputList = List.of(8, 78, -3, 53, 0, -54864, 568, 2, -23, 566);
        log.info(inputList.toString());
        List<Integer> sortedList = radixSort.sort(inputList, Integer::compare);
        log.info(sortedList.toString());
        assertEquals(inputList.stream().sorted().toList().toString(), sortedList.toString());
    }

    @Test
    void sortEmptyList() {
        RadixSort<Integer> radixSort = new RadixSort<>();
        List<Integer> emptyList = List.of();
        assertEquals(emptyList, radixSort.sort(emptyList, Integer::compare));
    }
}