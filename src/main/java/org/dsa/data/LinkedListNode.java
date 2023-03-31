package org.dsa.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkedListNode<T> {
    private T value;
    private LinkedListNode<T> previous;
    private LinkedListNode<T> next;
}
