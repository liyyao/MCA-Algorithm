package major.stack;

import common.CommonNode;

/**
 * 用链表实现栈
 */
public class LinkedStack<V> implements Stack_<V> {

    private int size;
    private CommonNode<V> head;

    public LinkedStack() {
        this.head = null;
        this.size = 0;
    }


    public void push(V e) {
        CommonNode<V> cur = new CommonNode<>(e);
        if (head == null) {
            head = cur;
        } else {
            cur.next = head;
            head = cur;
        }
        size++;
    }

    public V pop() {
        V ans = null;
        if (head != null) {
            ans = head.e;
            head = head.next;
            size--;
        }
        return ans;
    }

    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V peek() {
        return head != null ? head.e : null;
    }
}
