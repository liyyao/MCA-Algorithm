package major.stack;


public interface Stack_<V> {

    int size();

    boolean isEmpty();

    void push(V v);

    V pop();

    V peek();
}
