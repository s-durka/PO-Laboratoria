package bst;

public class InternalNode<T extends Comparable<T>> implements Node<T> {

    private Node<T> left;
    private Node<T> right;
    private T x;

    public InternalNode(T x) {
        left = new EmptyNode();
        right = new EmptyNode();
        this.x = x;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean contains(T x) {
        if(x.compareTo(this.x) > 0)
            return right.contains(x);
        else if(x.compareTo(this.x) < 0)
            return left.contains(x);
        else
            return true;
    }

    public Node add(T x) {
        if(x.compareTo(this.x) > 0)
            right = right.add(x);
        else if(x.compareTo(this.x) < 0)
            left = left.add(x);
        return this;
    }

    public String toString() {
        return left +" "+ x + " " + right;
    }
}
