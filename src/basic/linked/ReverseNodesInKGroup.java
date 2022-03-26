package basic.linked;

/**
 * K个节点的组内逆序调整
 */
public class ReverseNodesInKGroup {

    public static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value) {
            this.value = value;
            next = null;
        }
    }

    public static class MyReverse<V> {

        public Node<V> reverseKGroup(Node<V> head, int k) {
            Node<V> start = head;
            Node<V> end = getGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            head = end;
            reverse(start, end);
            Node<V> lastEnd = start;
            while (lastEnd.next != null) {
                start = lastEnd.next;
                end = getGroupEnd(start, k);
                if (end == null) {
                    return head;
                }
                reverse(start, end);
                lastEnd.next = end;
                lastEnd = start;
            }
            return head;
        }

        public Node<V> getGroupEnd(Node<V> node, int k) {
            while(--k != 0 && node != null) {
                node = node.next;
            }
            return node;
        }

        public void reverse(Node<V> start, Node<V> end) {
            end = end.next;
            Node<V> cur = start;
            Node<V> pre = null;
            Node<V> next = null;
            while (cur != end) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            start.next = end;
        }
    }

}
