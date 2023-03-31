package org.dsa.data.stack;

import org.dsa.data.LinkedListNode;

import java.util.*;

public class LinkedListStack<T> implements CustomStack<T> {
    private LinkedListNode<T> top = null;
    private int size = 0;

    public LinkedListStack() {
        
    }

    public LinkedListStack(Collection<T> list) {
        this();
        this.addAll(list);
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
        return containsOp(o);
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
    public boolean push(T item) {
        return addOp(item);
    }

    @Override
    public T remove() {
        if (size < 1) {
            throw new IllegalStateException("Stack is empty!");
        }
        return removeOp();
    }

    @Override
    public T pop() {
        return (size < 1 ? null : removeOp());
    }

    @Override
    public T element() {
        if (size < 1) {
            throw new IllegalStateException("Stack is empty!");
        }
        return peekOp();
    }

    @Override
    public T peek() {
        return (size < 1 ? null : peekOp());
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o: c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for(T t: c) {
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
        int sizeCopy = size;
        while (sizeCopy-- > 0) {
            removeOp();
        }
    }

    private boolean addOp(T t) {
        if (t == null) {
            return false;
        }
        LinkedListNode<T> linkedListNode = new LinkedListNode<>();
        linkedListNode.setValue(t);
        linkedListNode.setPrevious(top);
        linkedListNode.setNext(null);
        if (top == null) {
            top = linkedListNode;
        } else {
            top.setNext(linkedListNode);
            top = top.getNext();
        }
        size++;
        return true;
    }

    private T removeOp() {
        T result = top.getValue();
        top = top.getPrevious();
        size--;
        return result;
    }

    private T peekOp() {
        return top.getValue();
    }

    private boolean containsOp(Object value) {
        LinkedListNode<T> topCopy = top;
        while (topCopy != null) {
            if (Objects.equals(value, topCopy.getValue())) {
                return true; // found
            }
            topCopy = topCopy.getPrevious();
        }
        return false;
    }

    private List<T> toList() {
        List<T> list = new LinkedList<>();
        LinkedListNode<T> topCopy = top;
        while (topCopy != null) {
            list.add(topCopy.getValue());
            topCopy = topCopy.getPrevious();
        }
        Collections.reverse(list);
        return list;
    }
}
