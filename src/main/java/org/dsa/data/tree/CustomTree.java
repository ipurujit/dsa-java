package org.dsa.data.tree;

import org.dsa.data.BinaryTreeNode;

import java.util.Collection;

public interface CustomTree<T> extends Collection<T> {
    BinaryTreeNode<T> cloneAndGetRootNode();

    T remove();

    T element(); // throws exception on empty

    T peek(); // no exception on empty

    int getHeight();
}
