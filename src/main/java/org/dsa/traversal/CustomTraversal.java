package org.dsa.traversal;

import org.dsa.traversal.model.TraversalType;

import java.util.List;

public interface CustomTraversal<T> {
    List<T> traverse(TraversalType type);
}
