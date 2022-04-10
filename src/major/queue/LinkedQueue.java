package major.queue;

public class LinkedQueue<V> implements Queue_<V> {

    public static class Node<V> {
        public V value;
        public Node<V> last;
        public Node<V> next;

        public Node(V value) {
            this.value = value;
        }
    }

    public Node<V> head;
    public Node<V> tail;

    @Override
    public void addFromHead(V v) {
        Node<V> cur = new Node<>(v);
        if (head == null) {
            head = cur;
            tail = cur;
        } else {
            cur.next = head;
            head.last = cur;
            head = cur;
        }
    }

    @Override
    public void addFromBottom(V v) {
        Node<V> cur = new Node<V>(v);
        if (head == null) {
            head = cur;
            tail = cur;
        } else {
            cur.last = tail;
            tail.next = cur;
            tail = cur;
        }
    }

    @Override
    public V popFromHead() {
        if (head == null) {
            return null;
        }
        Node<V> cur = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.last = null;
            cur.next = null;
        }
        return cur.value;
    }

    @Override
    public V popFromBottom() {
        if (head == null) {
            return null;
        }
        Node<V> cur = tail;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.last;
            cur.last = null;
            tail.next = null;
        }
        return cur.value;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
