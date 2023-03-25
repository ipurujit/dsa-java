package org.dsa.sorting.bubble;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BubbleSortTest {

    private static final Logger log = Logger.getLogger(BubbleSortTest.class.getName());

    @Test
    void sort() {
        BubbleSort<Integer> bubbleSort = new BubbleSort<>();
        List<Integer> inputList = List.of(8, 78, 53, 568, 2, 566);
        log.info(inputList.toString());
        List<Integer> sortedList = bubbleSort.sort(inputList, Integer::compare);
        log.info(sortedList.toString());
        assertEquals(inputList.stream().sorted().toList(), sortedList);
    }
}