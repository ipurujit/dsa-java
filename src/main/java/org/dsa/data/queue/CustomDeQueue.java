package org.dsa.data.queue;

import java.util.Queue;

public interface CustomDeQueue<T> extends Queue<T> {
    boolean offerFirst(T item);
    boolean offerLast(T item);
    T removeFirst(); // throws exception on empty
    T removeLast(); // throws exception on empty
    T pollFirst();
    T pollLast();
    T elementFirst(); // throws exception on empty
    T elementLast(); // throws exception on empty
    T peekFirst();
    T peekLast();
}
