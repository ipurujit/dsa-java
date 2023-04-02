package org.dsa.data.queue;

import org.dsa.data.LinkedListNode;

import java.util.*;

public class LinkedListDeQueue<T> implements CustomDeQueue<T> {
    private static final String EMPTY_MESSAGE = "Queue is empty!";
    private LinkedListNode<T> first = null;
    private LinkedListNode<T> last = null;
    private int size = 0;

    public LinkedListDeQueue() {
        
    }

    public LinkedListDeQueue(Collection<T> list) {
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
        return addOp(t, true);
    }

    @Override
    public boolean offer(T t) {
        return offerLast(t);
    }

    @Override
    public boolean offerFirst(T item) {
        return addOp(item, false);
    }

    @Override
    public boolean offerLast(T item) {
        return addOp(item, true);
    }

    @Override
    public T remove() {
        return removeFirst();
    }

    @Override
    public T removeFirst() {
        if (size < 1) {
            throw new IllegalStateException(EMPTY_MESSAGE);
        }
        return removeOp(true);
    }

    @Override
    public T removeLast() {
        if (size < 1) {
            throw new IllegalStateException(EMPTY_MESSAGE);
        }
        return removeOp(false);
    }

    @Override
    public T poll() {
        return pollFirst();
    }

    @Override
    public T pollFirst() {
        return (size < 1 ? null : removeOp(true));
    }

    @Override
    public T pollLast() {
        return (size < 1 ? null : removeOp(false));
    }

    @Override
    public T element() {
        return elementFirst();
    }

    @Override
    public T elementFirst() {
        if (size < 1) {
            throw new IllegalStateException(EMPTY_MESSAGE);
        }
        return peekOp(true);
    }

    @Override
    public T elementLast() {
        if (size < 1) {
            throw new IllegalStateException(EMPTY_MESSAGE);
        }
        return peekOp(false);
    }

    @Override
    public T peek() {
        return peekFirst();
    }

    @Override
    public T peekFirst() {
        return (size < 1 ? null : peekOp(true));
    }

    @Override
    public T peekLast() {
        return (size < 1 ? null : peekOp(false));
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
            if (!addOp(t, true)) {
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
            removeOp(true);
        }
    }

    private boolean addOp(T t, boolean toLast) {
        if (t == null) {
            return false;
        }
        LinkedListNode<T> linkedListNode = new LinkedListNode<>();
        linkedListNode.setValue(t);
        if (toLast) {
            // add to end
            linkedListNode.setPrevious(last);
            linkedListNode.setNext(null);
        } else {
            // add to begin
            linkedListNode.setPrevious(null);
            linkedListNode.setNext(first);
        }
        if (first == null) {
            first = linkedListNode;
        } else if (!toLast) {
            // move first as adding to first
            first.setPrevious(linkedListNode);
            first = first.getPrevious();
        }
        if (last == null) {
            last = linkedListNode;
        } else if (toLast) {
            // move last as adding to last
            last.setNext(linkedListNode);
            last = last.getNext();
        }
        size++;
        return true;
    }

    private T removeOp(boolean fromFirst) {
        // default op for queue is remove from first i.e. start
        T result;
        if (fromFirst) {
            result = first.getValue();
            first = first.getNext();
        } else {
            result = last.getValue();
            last = last.getPrevious();
        }
        size--;
        if (size == 0) {
            // then first n last change
            first = null;
            last = null;
        }
        return result;
    }

    private T peekOp(boolean fromFirst) {
        return fromFirst ? first.getValue() : last.getValue();
    }

    private boolean containsOp(Object value) {
        LinkedListNode<T> firstCopy = first;
        while (firstCopy != null) {
            if (Objects.equals(value, firstCopy.getValue())) {
                return true; // found
            }
            firstCopy = firstCopy.getNext();
        }
        return false;
    }

    private List<T> toList() {
        List<T> list = new LinkedList<>();
        LinkedListNode<T> firstCopy = first;
        while (firstCopy != null) {
            list.add(firstCopy.getValue());
            firstCopy = firstCopy.getNext();
        }
        return list;
    }
}
