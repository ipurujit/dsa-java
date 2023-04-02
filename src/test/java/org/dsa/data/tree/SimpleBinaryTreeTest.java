package org.dsa.data.tree;

import org.dsa.data.BinaryTreeNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class SimpleBinaryTreeTest {
    private static final Logger log = Logger.getLogger(SimpleBinaryTreeTest.class.getName());

    private final SimpleBinaryTree<Integer> tree = new SimpleBinaryTree<>();

    private void negateNode(BinaryTreeNode<Integer> node) {
        if (node != null) {
            node.setValue(Math.negateExact(node.getValue()));
            negateNode(node.getLeftChild());
            negateNode(node.getRightChild());
        }
    }

    @Test
    void cloneAndGetRootNode() {
        Set<Integer> set = Set.of(1, 5, 2, 6, 3, 7, 23, 4, 21);
        SimpleBinaryTree<Integer> tree2 = new SimpleBinaryTree<>(set);
        BinaryTreeNode<Integer> root = tree2.cloneAndGetRootNode();
        // try changing all values using root and then compare
        // the original tree must not contain any negative values
        negateNode(root);
        // now compare
        assertTrue(Arrays.stream(tree2.toArray())
                .noneMatch(num -> set.contains(Math.negateExact((Integer) num))));
    }

    @Test
    void isEmpty() {
        tree.clear();
        assertEquals(0, tree.getHeight());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
    }

    @Test
    void contains() {
        tree.clear();
        tree.add(10);
        assertTrue(tree.contains(10));
        tree.clear();
        assertFalse(tree.contains(10));
    }

    @Test
    void iterator() {
        assertNotNull(tree.iterator());
    }

    @Test
    void toArray() {
        tree.clear();
        tree.add(10);
        tree.add(30);
        tree.add(20);
        assertNotNull(tree.toArray());
    }

    @Test
    void toArrayWithArg() {
        assertNotNull(tree.toArray(new Integer[]{10}));
    }

    @Test
    void add() {
        assertFalse(tree.add(null));
        assertTrue(tree.add(10));
    }

    @Test
    void remove() {
        tree.clear();
        assertThrows(IllegalStateException.class, tree::remove);
        tree.add(10);
        assertEquals(10, tree.remove());
        assertEquals(0, tree.getHeight());
        assertEquals(0, tree.size());
        assertFalse(tree.remove(10));
    }

    @Test
    void element() {
        tree.clear();
        assertThrows(IllegalStateException.class, tree::element);
        tree.add(10);
        assertEquals(10, tree.element());
        assertEquals(1, tree.getHeight());
        assertEquals(1, tree.size());
    }

    @Test
    void peek() {
        tree.clear();
        assertNull(tree.peek());
        tree.add(10);
        assertEquals(10, tree.peek());
        assertEquals(1, tree.getHeight());
        assertEquals(1, tree.size());
    }

    @Test
    void containsAll() {
        tree.clear();
        tree.add(10);
        tree.add(20);
        tree.add(15);
        assertTrue(tree.containsAll(List.of(10, 15, 20)));
        tree.add(null);
        List<Integer> list = new LinkedList<>();
        list.add(null);
        assertFalse(tree.containsAll(list));
    }

    @Test
    void addAll() {
        List<Integer> initList = List.of(0, 1, 4, 5, 2, 6, 9, 34, 32, 65, 54);
        tree.clear();
        tree.addAll(initList);
        assertEquals(Math.floor(Math.log(initList.size()) / Math.log(2)) + 1, tree.getHeight());
        for (int idx = 0; idx < initList.size(); idx++) {
            assertEquals(initList.get(initList.size() - 1 - idx), tree.remove());
        }
        assertThrows(IllegalStateException.class, tree::remove);
        List<Integer> list = new LinkedList<>();
        list.add(null);
        assertFalse(tree.addAll(list));
    }

    @Test
    void addAllConstructor() {
        SimpleBinaryTree<Integer> tree2 = new SimpleBinaryTree<>(List.of(10, 20));
        assertEquals(20, tree2.remove());
        assertEquals(10, tree2.remove());
        assertThrows(IllegalStateException.class, tree::remove);
    }

    @Test
    void removeAll() {
        assertFalse(tree.removeAll(List.<Integer>of()));
    }

    @Test
    void retainAll() {
        assertFalse(tree.retainAll(List.<Integer>of()));
    }

}