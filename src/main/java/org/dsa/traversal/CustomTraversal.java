package org.dsa.traversal;

import org.dsa.data.model.TraversalType;

import java.util.List;

public interface CustomTraversal<T> {
    List<T> traverse(TraversalType type);
}
