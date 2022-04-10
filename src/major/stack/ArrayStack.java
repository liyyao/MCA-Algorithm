package major.stack;

/**
 * 用数组实现栈
 */
public class ArrayStack<V> implements Stack_<V> {

    private int index;
    private V[] value;

    public ArrayStack() {
        this.index = 0;
        this.value = (V[]) new Object[8];
    }

    @Override
    public int size() {
        return index == 0 ? 0 : index - 1;
    }

    @Override
    public boolean isEmpty() {
        return index == 0;
    }

    @Override
    public void push(V v) {
        if (index == value.length) {
            throw new RuntimeException("栈满了");
        }
        value[index++] = v;
    }

    @Override
    public V pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空了");
        }
        return value[--index];
    }

    @Override
    public V peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈空了");
        }
        return value[index-1];
    }
}
