package org.dsa.traversal;

import lombok.Getter;
import org.dsa.data.BinaryTreeNode;
import org.dsa.data.model.TraversalType;
import org.dsa.data.tree.CustomTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Getter
public class TreeTraversal<T> implements CustomTraversal<T> {
    private final CustomTree<T> data;

    public TreeTraversal(CustomTree<T> data) {
        this.data = data;
    }

    @Override
    public List<T> traverse(TraversalType type) {
        List<T> result = null;
        if (Objects.requireNonNull(type) == TraversalType.INORDER) {
            result = inorderTraversal();
        }
        return result;
    }

    private void inorderOp(BinaryTreeNode<T> node, List<T> list) {
        if (node != null) {
            list.add(node.getValue());
            inorderOp(node.getLeftChild(), list);
            inorderOp(node.getRightChild(), list);
        }
    }

    private List<T> inorderTraversal() {
        List<T> list = null;
        BinaryTreeNode<T> node = data.cloneAndGetRootNode();
        if (node != null) {
            list = new LinkedList<>();
            inorderOp(node, list);
        }
        return list;
    }
}
