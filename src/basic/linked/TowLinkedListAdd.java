package basic.linked;

/**
 * 两个链表相加
 * 给定两个链表的头节点head1和head2
 * 认为从左到右是某个数字从低位到高位，返回相加之后的链表
 * 例子 4->3->6 2->5->3
 * 返回 6->8->9
 * 解释 634 + 355 = 986
 */
public class TowLinkedListAdd {

    public static void main(String[] args) {
        Node node1 = new Node(8);
        node1.next = new Node(4);
        node1.next.next = new Node(7);

        Node node2 = new Node(2);
        node2.next = new Node(5);
        node2.next.next = new Node(3);

        Node node = linkedListAdd(node1, node2);
        print(node1);
        print(node2);
        print(node);
    }

    static class Node {
        int value;
        Node next;
        public Node(int value) {
            this.value = value;
        }
    }

    public static void print(Node node) {
        while (node != null) {
            System.out.print(node.value + "->");
            node = node.next;
        }
        System.out.print("null");
        System.out.println();
    }

    public static Node linkedListAdd(Node node1, Node node2) {
        if (node1 == null && node2 == null) {
            return null;
        }
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        int nodeLen1 = linkedListLen(node1);
        int nodeLen2 = linkedListLen(node2);
        Node l = nodeLen1 < nodeLen2 ? node2 : node1;
        Node s = l == node1 ? node2 : node1;

        int d = 0; //进位
        int lValue = l.value;
        int sValue = s.value;
        int ans = lValue + sValue;
        d = ans / 10;
        int value = ans % 10;
        Node head = new Node(value);
        Node pre = head;
        Node lEnd = l.next;
        Node sEnd = s.next;
        while (lEnd != null) {
            if (sEnd == null) {
                sValue = 0;
            } else {
                sValue = sEnd.value;
                sEnd = sEnd.next;
            }
            ans = lEnd.value + sValue + d;
            d = ans / 10;
            value = ans % 10;
            Node temp = new Node(value);
            pre.next = temp;
            pre = temp;
            lEnd = lEnd.next;
        }
        if (d != 0) {
            pre.next = new Node(d);
        }
        return head;
    }

    public static int linkedListLen(Node node) {
        int len = 0;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }
}
