package org.dsa.data.stack;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListStack<T> implements CustomStack<T> {
    private final List<T> list;

    public LinkedListStack() {
        this.list = new LinkedList<>();
    }

    public LinkedListStack(Collection<T> list) {
        this();
        this.addAll(list);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return list.toArray(a);
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
        if (list.isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }
        return removeOp();
    }

    @Override
    public T pop() {
        return (list.isEmpty() ? null : removeOp());
    }

    @Override
    public T element() {
        if (list.isEmpty()) {
            throw new IllegalStateException("Stack is empty!");
        }
        return peekOp();
    }

    @Override
    public T peek() {
        return (list.isEmpty() ? null : peekOp());
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
        list.clear();
    }

    private boolean addOp(T t) {
        return list.add(t);
    }

    private T removeOp() {
        return list.remove(list.size() - 1);
    }

    private T peekOp() {
        return list.get(list.size() - 1);
    }
}
