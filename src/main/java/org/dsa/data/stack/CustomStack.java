package org.dsa.data.stack;

import java.util.Collection;

public interface CustomStack<T> extends Collection<T> {
    boolean push(T item);
    T remove(); // throws exception on empty
    T pop();
    T element(); // throws exception on empty
    T peek();
}
