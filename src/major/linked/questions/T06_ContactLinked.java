package major.linked.questions;

import common.Node;
import tool.LinkedUtil;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * 题目6：
 * 给定一个链表，链表长度为偶数N
 * 请实现链表前N/2个节点和后N/2个节点的逆序节点交叉串联起来
 * 例：L1->L2->L3->L4->R1->R2->R3->R4 这样一个链表，
 * 按要求串联后的结果是：L1->R4->L2->R3->L3->R2->L4->R1
 */
public class T06_ContactLinked {

    public static void main(String[] args) {
        int testTimes = 10000000;
        int maxLen = 100;
        int maxVal = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Node node = LinkedUtil.generateEvenSizeRandomLinkedList(maxLen, maxVal);
            Node ans = test(node);
            Node ans2 = splitAndContactLinked(node);
            while (ans != null && ans2 != null) {
                if (ans.val != ans2.val) {
                    System.out.println("出错了...");
                    return;
                }
                ans = ans.next;
                ans2 = ans2.next;
            }
            if (ans != null || ans2 != null) {
                System.out.println("出错了....");
                return;
            }
        }
        System.out.println("测试结束");
    }

    public static Node splitAndContactLinked(Node head) {
        if (head == null) {
            return null;
        }
        int size = 0;
        Node cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        cur = head;
        int count = 1;
        while (count < size / 2) {
            cur = cur.next;
            count++;
        }
        Node next = cur.next;
        cur.next = null;
        Node reverse = reverseLinked(next);
        cur = head;
        while (cur != null) {
            next = cur.next;
            cur.next = reverse;
            reverse = reverse.next;
            cur.next.next = next;
            cur = next;
        }
        return head;
    }

    public static Node reverseLinked(Node head) {
        if (head == null) {
            return null;
        }
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node test(Node head) {
        List<Node> nodeList = new ArrayList<>();
        Node cur = head;
        Node pre = null;
        while (cur != null) {
            pre = cur;
            nodeList.add(cur);
            cur = cur.next;
            pre.next = null;
        }
        int l = 0;
        int r = nodeList.size() - 1;
        cur = null;
        while (l < r) {
            if (cur == null) {
                cur = nodeList.get(l++);
                head = cur;
            } else {
                cur.next = nodeList.get(l++);
                cur = cur.next;
            }
            cur.next = nodeList.get(r--);
            cur = cur.next;
        }
        return head;
    }
}
