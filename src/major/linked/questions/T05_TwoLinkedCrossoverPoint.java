package major.linked.questions;

import common.Node;

/**
 * 题目5：
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点，如果不相交，返回null
 * 要求：如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1).
 */
public class T05_TwoLinkedCrossoverPoint {

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).val);

        // 0->9->8->6->7->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).val);
    }

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node point1 = getLoopNode(head1);
        Node point2 = getLoopNode(head2);
        if (point1 == null && point2 == null) {
            return noLoop(head1, head2);
        }
        if (point1 != null && point2 != null) {
            return bothLoop(head1, point1, head2, point2);
        }
        return null;
    }

    /**
     * 两个链表都有环的情况下，求相交节点
     * @param head1 链表1
     * @param point1 链表1的入环节点
     * @param head2 链表2
     * @param point2 链表2的入环节点
     * @return 链表1和链表2的交点
     */
    private static Node bothLoop(Node head1, Node point1, Node head2, Node point2) {
        Node cur1 = null;
        Node cur2 = null;
        if (point1 == point2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != point1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != point2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            cur1 = point1.next;
            while (cur1 != point1) {
                if (cur1 == point2) {
                    return point1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    /**
     * 老师的方法，比自己的方法要好，代码量少
     * @param head1
     * @param head2
     * @return
     */
    private static Node noLoop2(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        cur1 = n > 0 ? head1 : head2;           //谁长，谁的头变成cur1
        cur2 = cur1 == head1 ? head2 : head1;   //谁短，谁的头变成cur2
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    /**
     * 两个链表都无环的情况下，求相交节点
     * @param head1
     * @param head2
     * @return
     */
    private static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        int len1 = 0;
        int len2 = 0;
        Node cur = head1;
        while (cur != null) {
            len1++;
            cur = cur.next;
        }
        cur = head2;
        while (cur != null) {
            len2++;
            cur = cur.next;
        }
        Node longNode = len1 >= len2 ? head1 : head2;
        Node shortNode = len1 < len2 ? head1 : head2;
        int step = Math.abs(len1 - len2);
        for (int i = 0; i < step; i++) {
            longNode = longNode.next;
        }
        while (longNode != null) {
            if (longNode == shortNode) {
                return longNode;
            }
            longNode = longNode.next;
            shortNode = shortNode.next;
        }
        return null;
    }

    /**
     * 查找有环链表的第一个入环节点
     * 如果链表无环，则返回null
     * @param head
     * @return
     */
    private static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
