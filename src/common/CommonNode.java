package common;

public class CommonNode<V> {

    public V e;
    public CommonNode<V> next;

    public CommonNode(V e) {
        this.e = e;
    }
}
