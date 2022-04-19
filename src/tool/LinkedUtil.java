package tool;

import common.DoubleNode;
import common.Node;

public class LinkedUtil {

    public static Node copyLinked(Node head) {
        if (head == null) {
            return null;
        }
        Node node = new Node(head.val);
        Node ans = node;
        head = head.next;
        while (head != null) {
            node.next = new Node(head.val);
            node = node.next;
            head = head.next;
        }
        return ans;
    }

    /**
     * 打印链表
     * @param head
     */
    public static void printLinkedList(Node head) {
        if (head == null) {
            return;
        }
        Node next = head;
        while (next != null) {
            System.out.print(next.val + "->");
            next = next.next;
        }
        System.out.println("null");
    }

    /**
     * 生成单链表
     * @param len
     * @param value
     * @return
     */
    public static Node generateRandomLinkedList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        Node head = new Node((int) (Math.random() * (value + 1)));
        Node pre = head;
        while (size != 0) {
            Node cur = new Node((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }

    /**
     * 生成回文单链表
     * @param len
     * @param value
     * @return
     */
    public static Node generatePalindromeLinkedList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        int arrLen = size / 2;
        size -= arrLen;
        Node[] arr = null;
        if (arrLen > 0) {
            arr = new Node[arrLen];
        }
        Node head = new Node((int) (Math.random() * (value + 1)));
        size--;
        if (arr != null) {
            arr[--arrLen] = new Node(head.val);
        }
        Node pre = head;
        while (size != 0) {
            Node cur = new Node((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            size--;
            if (arrLen > 0) {
                arr[--arrLen] = new Node(cur.val);
            }
        }
        if (arr != null) {
            for (Node node : arr) {
                pre.next = node;
                pre = node;
            }
        }
        return head;
    }

    /**
     * 生成双链表
     * @param len
     * @param value
     * @return
     */
    public static DoubleNode generateRandomDoubleList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        size--;
        DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
        DoubleNode pre = head;
        while (size != 0) {
            DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
            pre.next = cur;
            cur.last = pre;
            pre = cur;
            size--;
        }
        return head;
    }
}
