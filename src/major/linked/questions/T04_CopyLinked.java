package major.linked.questions;

import java.util.*;

/**
 * https://leetcode.com/problems/copy-list-with-random-pointer/submissions/
 * 一种特殊的单链表节点类描述如下：
 * class Node {
 *     int value;
 *     Node next;
 *     Node random;
 *
 *     public Node(int val) {
 *         this.value = val;
 *     }
 * }
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * 要求：时间复杂度O(N),额外空间复杂度O(1).
 */
public class T04_CopyLinked {

    public static void main(String[] args) {
        int testTime = 1;
        int maxLen = 10;
        int maxValue = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node node = generateRandomLinkedList(maxLen, maxValue);
            printLinkedList(node);
            Node copyNode = copyRandList(node);
            printLinkedList(copyNode);
        }
        System.out.println("测试结束");
    }


    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int value) {
            this.val = value;
        }
    }

    private static Node copyRandList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        Node ans = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next;
            cur.next = next.next;
            if (next.next != null) {
                next.next = next.next.next;
            }
            cur = cur.next;
        }
        return ans;
    }

    private static Node copyRandList1(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static Node generateRandomLinkedList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));
        if (size == 0) {
            return null;
        }
        Node head = new Node((int) (Math.random() * (value + 1)));
        Node pre = head;
        List<Node> nodeList = new ArrayList<>();
        nodeList.add(null);
        nodeList.add(head);
        int index = size - 1;
        while (index != 0) {
            Node cur = new Node((int) (Math.random() * (value + 1)));
            pre.next = cur;
            pre = cur;
            index--;
            nodeList.add(cur);
        }
        pre = head;
        while (pre != null) {
            int d = (int) (Math.random() * size);
            pre.random = nodeList.get(d);
            pre = pre.next;
        }
        return head;
    }

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
}
