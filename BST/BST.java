package bst;

public class BST<T extends Comparable<T>> implements Node<T> {

    private Node<T> root;

    public BST() {
        root = new EmptyNode();
    }

    public boolean isEmpty() {
        return root.isEmpty();
    }

    public boolean contains(T x) {
        return root.contains(x);
    }

    public Node add(T x) {
        root = root.add(x);
        return root;
    }

    @Override
    public String toString() { //wypisuje infix BST
        return root.toString();
    }
}
