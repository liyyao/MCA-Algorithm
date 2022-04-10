package major.queue;

public class ArrayQueue<V> implements Queue_<V> {
    private V[] arr;
    private int pushi;  //end
    private int polli;  //begin
    private int size;
    private final int limit;

    public ArrayQueue(int limit) {
        arr = (V[]) new Object[limit];
        pushi = 0;
        polli = 0;
        size = 0;
        this.limit = limit;
    }

    @Override
    public void addFromHead(V v) {
        if (size == limit) {
            throw new RuntimeException("队列满了，不能再增加了");
        }
        polli = preIndex(polli);
        arr[polli] = v;
        size++;
    }

    @Override
    public void addFromBottom(V v) {
        if (size == limit) {
            throw new RuntimeException("队列满了，不能再增加了");
        }
        arr[pushi] = v;
        pushi = nextIndex(pushi);
        size++;
    }

    @Override
    public V popFromHead() {
        if (size == 0) {
            throw new RuntimeException("队列空了，不能再拿了");
        }
        V v = arr[polli];
        polli = nextIndex(polli);
        size--;
        return v;
    }

    @Override
    public V popFromBottom() {
        if (size == 0) {
            throw new RuntimeException("队列空了，不能再拿了");
        }
        V v = arr[pushi];
        pushi = preIndex(pushi);
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int nextIndex(int i) {
        return i < limit - 1 ? i + 1 : 0;
    }

    private int preIndex(int i) {
        return i == 0 ? limit - 1 : i - 1;
    }
}
