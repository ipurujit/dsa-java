package org.dsa.data.stack;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListStackTest {
    private static final Logger log = Logger.getLogger(LinkedListStackTest.class.getName());

    private final LinkedListStack<Integer> stack = new LinkedListStack<>();

    @Test
    void isEmpty() {
        stack.clear();
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    @Test
    void contains() {
        stack.clear();
        stack.push(10);
        assertTrue(stack.contains(10));
    }

    @Test
    void iterator() {
        assertNotNull(stack.iterator());
    }

    @Test
    void toArray() {
        assertNotNull(stack.toArray());
    }

    @Test
    void toArrayWithArg() {
        assertNotNull(stack.toArray(new Integer[]{10}));
    }

    @Test
    void remove() {
        stack.clear();
        assertThrows(IllegalStateException.class, stack::remove);
        stack.add(10);
        assertEquals(10, stack.remove());
        assertEquals(0, stack.size());
    }

    @Test
    void pop() {
        stack.clear();
        assertNull(stack.pop());
        stack.add(10);
        assertEquals(10, stack.pop());
        assertEquals(0, stack.size());
    }

    @Test
    void element() {
        stack.clear();
        assertThrows(IllegalStateException.class, stack::element);
        stack.add(10);
        assertEquals(10, stack.element());
        assertEquals(1, stack.size());
    }

    @Test
    void peek() {
        stack.clear();
        assertNull(stack.peek());
        stack.add(10);
        assertEquals(10, stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    void containsAll() {
        stack.clear();
        stack.add(10);
        stack.add(20);
        assertTrue(stack.containsAll(List.of(10, 20)));
    }

    @Test
    void addAll() {
        stack.clear();
        stack.addAll(List.of(10, 20));
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
        assertNull(stack.pop());
    }

    @Test
    void addAllConstructor() {
        LinkedListStack<Integer> stack2 = new LinkedListStack<>(List.of(10, 20));
        assertEquals(20, stack2.pop());
        assertEquals(10, stack2.pop());
        assertNull(stack2.pop());
    }

    @Test
    void removeAll() {
        assertFalse(stack.removeAll(List.<Integer>of()));
    }

    @Test
    void retainAll() {
        assertFalse(stack.retainAll(List.<Integer>of()));
    }
}