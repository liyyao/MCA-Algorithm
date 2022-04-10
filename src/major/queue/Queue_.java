package major.queue;

public interface Queue_<V> {

    void addFromHead(V v);

    void addFromBottom(V v);

    V popFromHead();

    V popFromBottom();

    boolean isEmpty();
}
