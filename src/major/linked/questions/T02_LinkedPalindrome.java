package major.linked.questions;

import common.Node;
import tool.LinkedUtil;

import java.util.Stack;

/**
 * 题目2：
 * 给定一个单链表的头节点head，请判断该链表是否为回文结构
 * 1、哈希表（或栈）方法特别简单（笔试用）
 * 2、改原链表的方法就需要注意边界了（面试用）
 */
public class T02_LinkedPalindrome {

    public static void main(String[] args) {
        int testTime = 1000000;
        int len = 100;
        int value = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node head = null;
            if (Math.random() < 0.5) {
                head = LinkedUtil.generatePalindromeLinkedList(len, value);
            } else {
                head = LinkedUtil.generateRandomLinkedList(len, value);
            }
            boolean test = test(head);
            boolean isPalindrome = isPalindromeLinked(head);
            if (test != isPalindrome) {
                System.out.println("出错了...");
                return;
            }
        }
        System.out.println("测试结束");
    }

    public static boolean isPalindromeLinked(Node root) {
        if (root == null || root.next == null) {
            return true;
        }
        Node stepOneNode = root;
        Node stepTwoNode = root;
        while (stepTwoNode != null) {
            if (stepTwoNode.next == null || stepTwoNode.next.next == null) {
                break;
            }
            stepOneNode = stepOneNode.next;
            stepTwoNode = stepTwoNode.next.next;
        }
        Node reverseNode = reverseLinked(stepOneNode.next);
        stepOneNode.next = null;
        boolean isPalindrome = checkLinkedEquals(root, reverseNode);
        stepOneNode.next = reverseLinked(reverseNode);
        return isPalindrome;
    }

    public static Node reverseLinked(Node head) {
        Node pre = null;
        Node next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static boolean checkLinkedEquals(Node root1, Node root2) {
        if (root1 != null && root2 == null || root1 == null && root2 != null) {
            return false;
        }
        while (root1 != null && root2 != null) {
            if (root1.val != root2.val) {
                return false;
            }
            root1 = root1.next;
            root2 = root2.next;
        }
        if (root1 != null) {
            return root1.next == null;
        }
        if (root2 != null) {
            return root2.next == null;
        }
        return true;
    }

    public static boolean test(Node root) {
        if (root == null || root.next == null) {
            return true;
        }
        Node node = root;
        Stack<Node> stack = new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.next;
        }
        while (root != null) {
            Node p = stack.pop();
            if (root.val != p.val) {
                return false;
            }
            root = root.next;
        }
        return true;
    }
}
