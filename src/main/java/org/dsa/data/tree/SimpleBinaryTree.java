package org.dsa.data.tree;

import org.dsa.data.BinaryTreeNode;
import org.dsa.data.queue.CustomDeQueue;
import org.dsa.data.queue.LinkedListDeQueue;

import java.util.*;
import java.util.logging.Logger;

public class SimpleBinaryTree<T> implements CustomTree<T> {
    private static final Logger log = Logger.getLogger(SimpleBinaryTree.class.getName());
    private static final String EMPTY_MESSAGE = "Tree is empty!";
    private final CustomDeQueue<BinaryTreeNode<T>> leafNodeQueue = new LinkedListDeQueue<>();
    private BinaryTreeNode<T> root = null;
    private int size = 0;
    private int height = 0;

    public SimpleBinaryTree() {
    }

    public SimpleBinaryTree(Collection<T> c) {
        this.addAll(c);
    }

    @Override
    public BinaryTreeNode<T> cloneAndGetRootNode() {
        // make a recursive copy! not the original!
        return new BinaryTreeNode<>(root);
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size < 1;
    }

    @Override
    public boolean contains(Object o) {
        return containsOp(List.of(o)).isEmpty();
    }

    @Override
    public Iterator<T> iterator() {
        return toList().iterator();
    }

    @Override
    public Object[] toArray() {
        return toList().toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return toList().toArray(a);
    }

    @Override
    public boolean add(T t) {
        return addOp(t);
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException(EMPTY_MESSAGE);
        }
        return removeOp();
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public T element() {
        if (isEmpty()) {
            throw new IllegalStateException(EMPTY_MESSAGE);
        }
        return peekOp();
    }

    @Override
    public T peek() {
        return (isEmpty() ? null : peekOp());
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        List<Object> list = new ArrayList<>(c);
        return containsOp(list).isEmpty();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T t : c) {
            if (!addOp(t)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
        height = 0;
        leafNodeQueue.clear();
    }

    private T peekOp() {
        // take the last node from leaf node queue
        BinaryTreeNode<T> node = leafNodeQueue.peekLast();
        // inside peek op, we assume there exists something to peek!
        return node.getValue();
    }

    private T removeOp() {
        // take the last node from leaf node queue
        // put its parent in begin of queue
        BinaryTreeNode<T> node = leafNodeQueue.pollLast();
        // inside remove op, we assume there exists something to remove!
        BinaryTreeNode<T> parent = node.getParent();
        if (parent != null && Objects.equals(parent.getRightChild(), node)) {
            // if node to be removed is right child, then parent must be re-added to queue
            leafNodeQueue.offerFirst(parent);
        }
        if (Integer.bitCount(size) == 1) {
            // that means on removing this node, height will decrease by 1
            height--;
        }
        size--;
        // check height?
        return node.getValue();
    }

    private boolean addOp(T item) {
        if (item == null) {
            return false;
        }
        BinaryTreeNode<T> node = new BinaryTreeNode<>();
        node.setValue(item);
        node.setLeftChild(null);
        node.setRightChild(null);
        // find parent
        BinaryTreeNode<T> parent = leafNodeQueue.poll();
        if (parent == null) {
            // this is root
            node.setParent(null);
            node.setLevel(1);
            root = node;
        } else {
            // not root
            node.setParent(parent);
            node.setLevel(parent.getLevel() + 1);
            // find out - insert as left or right child
            if (parent.getLeftChild() == null) {
                parent.setLeftChild(node);
                // right child can still be added so re-add to queue
                leafNodeQueue.offerFirst(parent);
            } else {
                parent.setRightChild(node);
            }
        }
        // update size and height
        size++;
        height = Math.max(height, node.getLevel());
        // append to queue
        leafNodeQueue.offer(node);
        return true;
    }

    private Set<Object> containsOp(Collection<Object> values) {
        if (isEmpty()) {
            return new HashSet<>(values);
        }
        Set<Object> valueSet = new HashSet<>(values);
        CustomDeQueue<BinaryTreeNode<T>> queue = new LinkedListDeQueue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> node = queue.poll();
            try {
                valueSet.remove(node.getValue());
            } catch (Exception e) {
                log.info("Error occurred while checking whether tree contains value");
            }
            if (node.getLeftChild() != null) {
                // add left child
                queue.offer(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                // add right child
                queue.offer(node.getRightChild());
            }
        }
        return valueSet;
    }

    private List<T> toList() {
        if (isEmpty()) {
            return List.of();
        }
        List<T> result = new LinkedList<>();
        CustomDeQueue<BinaryTreeNode<T>> queue = new LinkedListDeQueue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> node = queue.poll();
            result.add(node.getValue());
            if (node.getLeftChild() != null) {
                // add left child
                queue.offer(node.getLeftChild());
            }
            if (node.getRightChild() != null) {
                // add right child
                queue.offer(node.getRightChild());
            }
        }
        return result;
    }
}
