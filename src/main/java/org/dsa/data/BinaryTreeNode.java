package org.dsa.data;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class BinaryTreeNode<T> {
    private T value;
    private BinaryTreeNode<T> leftChild;
    private BinaryTreeNode<T> rightChild;
    private int level;
    // Very important to exclude parent otherwise infinite loop between parent and child
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private BinaryTreeNode<T> parent;

    public BinaryTreeNode(BinaryTreeNode<T> toCopy) {
        if (toCopy.getLeftChild() != null) {
            BinaryTreeNode<T> leftChildClone = new BinaryTreeNode<>(toCopy.getLeftChild());
            leftChildClone.setParent(this);
            this.setLeftChild(leftChildClone);
        }
        if (toCopy.getRightChild() != null) {
            BinaryTreeNode<T> rightChildClone = new BinaryTreeNode<>(toCopy.getRightChild());
            rightChildClone.setParent(this);
            this.setRightChild(rightChildClone);
        }
        // very important to set parent as null otherwise it could go to infinite recursion!
        this.setParent(null);
        this.setValue(toCopy.getValue());
        this.setLevel(toCopy.getLevel());
    }

    @Override
    public String toString() {
        // WARN: DO NOT ADD PARENT HERE!
        return "BinaryTreeNode[" +
                "value=" + value +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                ", level=" + level +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        // WARN: DO NOT ADD PARENT HERE!
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTreeNode<?> that = (BinaryTreeNode<?>) o;
        return level == that.level &&
                Objects.equals(value, that.value) &&
                Objects.equals(leftChild, that.leftChild) &&
                Objects.equals(rightChild, that.rightChild);
    }

    @Override
    public int hashCode() {
        // WARN: DO NOT ADD PARENT HERE!
        return Objects.hash(value, leftChild, rightChild, level);
    }
}
