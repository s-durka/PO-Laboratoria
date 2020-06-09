package bst;

public interface Node <T extends Comparable<T>> {

    boolean isEmpty();

    boolean contains(T x);

    Node add(T x);

}
