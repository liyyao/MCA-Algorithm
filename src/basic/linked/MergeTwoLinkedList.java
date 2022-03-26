package basic.linked;

/**
 * 两个有序链表的合并
 * 给定两个有序链表的头节点head1和head2，返回合并之后的大链表，要求依然有序
 * 例子 1->3->3->5->7  2->2->3->3->7
 * 返回 1->2->2->3->3->3->3->5->7->7
 */
public class MergeTwoLinkedList {

    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(3);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(5);
        n1.next.next.next.next = new Node(7);

        Node n2 = new Node(2);
        n2.next = new Node(2);
        n2.next.next = new Node(3);
        n2.next.next.next = new Node(3);
        n2.next.next.next.next = new Node(7);

        print(n1);
        print(n2);
        Node n = merge(n1, n2);
        print(n);
    }

    public static Node merge(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return node1 == null ? node2 : node1;
        }
        Node head = node1.value > node2.value ? node2 : node1;
        Node cur1 = head == node1 ? node1.next : node1;
        Node cur2 = head == node2 ? node2.next : node2;
        Node pre = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) {
                pre.next = cur1;
                cur1 = cur1.next;
            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        pre.next = cur1 != null ? cur1 : cur2;
        return head;
    }

    public static void print(Node node) {
        while (node != null) {
            System.out.print(node.value + "->");
            node = node.next;
        }
        System.out.print("null");
        System.out.println();
    }

    static class Node {
        int value;
        Node next;
        public Node(int value) {
            this.value = value;
        }
    }
}
