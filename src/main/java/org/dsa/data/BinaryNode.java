package org.dsa.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BinaryNode<T> {
    private T value;
    private BinaryNode<T> leftChild;
    private BinaryNode<T> rightChild;

}
