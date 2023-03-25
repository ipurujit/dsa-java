package org.dsa.data.heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBinaryHeapTest {
    private final ArrayBinaryHeap<Integer> arrayBinaryHeap = new ArrayBinaryHeap<>(Integer::compareTo);

    @BeforeEach
    void setUp() {
        arrayBinaryHeap.add((int) (Math.random() * 100));
    }

    @Test
    void size() {
        arrayBinaryHeap.clear();
        assertEquals(0, arrayBinaryHeap.size());
        arrayBinaryHeap.add(10);
        assertEquals(1, arrayBinaryHeap.size());
        arrayBinaryHeap.remove();
        assertEquals(0, arrayBinaryHeap.size());
    }

    @Test
    void isEmpty() {
        arrayBinaryHeap.clear();
        assertTrue(arrayBinaryHeap.isEmpty());
        arrayBinaryHeap.add(10);
        assertFalse(arrayBinaryHeap.isEmpty());
        arrayBinaryHeap.remove();
        assertTrue(arrayBinaryHeap.isEmpty());
    }

    @Test
    void contains() {
        arrayBinaryHeap.add(10);
        assertTrue(arrayBinaryHeap.contains(10));
    }

    @Test
    void add() {
        int beforeSize = arrayBinaryHeap.size();
        assertTrue(arrayBinaryHeap.add(10));
        assertEquals(beforeSize + 1, arrayBinaryHeap.size());
    }

    @Test
    void remove() {
        assertFalse(arrayBinaryHeap.remove(4));
        arrayBinaryHeap.add(10);
        int beforeSize = arrayBinaryHeap.size();
        arrayBinaryHeap.remove();
        assertEquals(beforeSize - 1, arrayBinaryHeap.size());
    }

    @Test
    void containsAll() {
        List<Integer> list = List.of(5,10,3,43);
        arrayBinaryHeap.addAll(list);
        assertTrue(arrayBinaryHeap.containsAll(list));
    }

    @Test
    void containsAllFalse() {
        arrayBinaryHeap.clear();
        assertFalse(arrayBinaryHeap.containsAll(List.of(1)));
    }

    @Test
    void addAll() {
        arrayBinaryHeap.clear();
        List<Integer> list = List.of(5,10,3,43,42,524,62,2,46,24,2,3);
        arrayBinaryHeap.addAll(list);
        assertEquals(list.size(), arrayBinaryHeap.size());
        // build a list by removing one by when
        Integer[] heapSortResult = new Integer[arrayBinaryHeap.size()];
        int idx = 0;
        while (!arrayBinaryHeap.isEmpty()) {
            heapSortResult[idx++] = arrayBinaryHeap.remove();
        }
        assertArrayEquals(heapSortResult, list.stream().sorted().toArray());
    }

    @Test
    void removeAll() {
        assertFalse(arrayBinaryHeap.removeAll(List.of(1,2,3)));
    }

    @Test
    void retainAll() {
        assertFalse(arrayBinaryHeap.retainAll(List.of(1,2,3)));
    }

    @Test
    void clear() {
        arrayBinaryHeap.clear();
        assertEquals(0, arrayBinaryHeap.size());
        List<Integer> list = List.of(5,10,3,43);
        arrayBinaryHeap.addAll(list);
        assertEquals(list.size(), arrayBinaryHeap.size());
        arrayBinaryHeap.clear();
        assertEquals(0, arrayBinaryHeap.size());
    }

    @Test
    void checkRemoveThrowException() {
        arrayBinaryHeap.clear();
        assertThrows(IllegalStateException.class, arrayBinaryHeap::remove);
    }

    @Test
    void checkPollNull() {
        arrayBinaryHeap.clear();
        assertNull(arrayBinaryHeap.poll());
    }

    @Test
    void checkElementException() {
        arrayBinaryHeap.clear();
        assertThrows(IllegalStateException.class, arrayBinaryHeap::element);
    }

    @Test
    void checkElement() {
        arrayBinaryHeap.add(1);
        assertNotNull(arrayBinaryHeap.element());
    }

    @Test
    void checkPeekNull() {
        arrayBinaryHeap.clear();
        assertNull(arrayBinaryHeap.peek());
    }

    @Test
    void checkOffer() {
        arrayBinaryHeap.offer(25);
        assertTrue(arrayBinaryHeap.contains(25));
    }

    @Test
    void checkIterators() {
        arrayBinaryHeap.add(25);
        assertTrue(arrayBinaryHeap.iterator().hasNext());
        assertTrue(arrayBinaryHeap.toArray().length > 0);
        assertTrue(arrayBinaryHeap.toArray(new Integer[]{20}).length > 0);
    }
}