package org.dsa.data.queue;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListDeQueueTest {

    private static final Logger log = Logger.getLogger(LinkedListDeQueueTest.class.getName());

    private final LinkedListDeQueue<Integer> queue = new LinkedListDeQueue<>();

    @Test
    void isEmpty() {
        queue.clear();
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test
    void contains() {
        queue.clear();
        queue.offer(10);
        assertTrue(queue.contains(10));
        queue.clear();
        assertFalse(queue.contains(10));
    }

    @Test
    void iterator() {
        assertNotNull(queue.iterator());
    }

    @Test
    void toArray() {
        queue.clear();
        queue.offer(10);
        assertNotNull(queue.toArray());
    }

    @Test
    void toArrayWithArg() {
        assertNotNull(queue.toArray(new Integer[]{10}));
    }

    @Test
    void add() {
        assertFalse(queue.add(null));
        assertTrue(queue.add(10));
    }

    @Test
    void addFirst() {
        queue.clear();
        assertTrue(queue.offerFirst(10));
        assertTrue(queue.contains(10));
        assertTrue(queue.offerFirst(20));
        assertTrue(queue.contains(20));
        assertEquals(20, queue.poll());
        assertEquals(10, queue.poll());
    }

    @Test
    void remove() {
        queue.clear();
        assertThrows(IllegalStateException.class, queue::remove);
        queue.add(10);
        assertEquals(10, queue.remove());
        assertEquals(0, queue.size());
        assertFalse(queue.remove(10));
    }

    @Test
    void removeLast() {
        queue.clear();
        assertThrows(IllegalStateException.class, queue::removeLast);
        queue.offerLast(10);
        assertEquals(10, queue.removeLast());
        assertFalse(queue.contains(10));
    }

    @Test
    void poll() {
        queue.clear();
        assertNull(queue.poll());
        queue.add(10);
        assertEquals(10, queue.poll());
        assertEquals(0, queue.size());
    }

    @Test
    void pollLast() {
        queue.clear();
        assertNull(queue.pollLast());
        queue.add(10);
        assertEquals(10, queue.pollLast());
        assertEquals(0, queue.size());
    }

    @Test
    void element() {
        queue.clear();
        assertThrows(IllegalStateException.class, queue::element);
        queue.add(10);
        assertEquals(10, queue.element());
        assertEquals(1, queue.size());
    }

    @Test
    void elementLast() {
        queue.clear();
        assertThrows(IllegalStateException.class, queue::elementLast);
        queue.add(10);
        assertEquals(10, queue.elementLast());
        assertEquals(1, queue.size());
    }

    @Test
    void peek() {
        queue.clear();
        assertNull(queue.peek());
        queue.add(10);
        assertEquals(10, queue.peek());
        assertEquals(1, queue.size());
    }

    @Test
    void peekLast() {
        queue.clear();
        assertNull(queue.peekLast());
        queue.add(10);
        assertEquals(10, queue.peekLast());
        assertEquals(1, queue.size());
    }

    @Test
    void containsAll() {
        queue.clear();
        queue.add(10);
        queue.add(20);
        assertTrue(queue.containsAll(List.of(10, 20)));
        queue.add(null);
        List<Integer> list = new LinkedList<>();
        list.add(null);
        assertFalse(queue.containsAll(list));
    }

    @Test
    void addAll() {
        queue.clear();
        queue.addAll(List.of(0, 1));
        assertEquals(0, queue.poll());
        assertEquals(1, queue.poll());
        assertNull(queue.poll());
        List<Integer> list = new LinkedList<>();
        list.add(null);
        assertFalse(queue.addAll(list));
    }

    @Test
    void addAllConstructor() {
        LinkedListDeQueue<Integer> stack2 = new LinkedListDeQueue<>(List.of(10, 20));
        assertEquals(10, stack2.poll());
        assertEquals(20, stack2.poll());
        assertNull(stack2.poll());
    }

    @Test
    void removeAll() {
        assertFalse(queue.removeAll(List.<Integer>of()));
    }

    @Test
    void retainAll() {
        assertFalse(queue.retainAll(List.<Integer>of()));
    }
}