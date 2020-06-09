package bst;

public class EmptyNode<T extends Comparable<T>> implements Node<T> {

    public EmptyNode() {
    }
    public boolean isEmpty() {
        return true;
    }

    public boolean contains(T x) {
        return false;
    }

    public Node add(T x) {
        return new InternalNode(x);
    }

    @Override
    public String toString() {
        return "";
    }
}
