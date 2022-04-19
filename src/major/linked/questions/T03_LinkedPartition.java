package major.linked.questions;

import common.Node;
import tool.IntUtil;
import tool.LinkedUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目3：
 * 给定一个值K，将单向链表按K值划分成左边小、中间相等、右边大的形式
 * 1、把链表放入数组里，在数组上做partition（笔试用）
 * 2、分成小、中、大三部分，再把各个部分之间串起来（面试用）
 */
public class T03_LinkedPartition {

    public static void main(String[] args) {
        multiTest();
        //singleTest();
    }

    private static void singleTest() {
        Node head = new Node(6);
        head.next = new Node(2);
        head.next.next = new Node(6);
        head.next.next.next = new Node(3);
        int k = 6;
        Node node = divideLInked(head, k);
        LinkedUtil.printLinkedList(node);
    }

    private static void multiTest() {
        int testTimes = 1000000;
        int maxLen = 100;
        int maxValue = 100;
        int maxK = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int k = IntUtil.randomPositiveNumber(maxK);
            Node head = LinkedUtil.generateRandomLinkedList(maxLen, maxValue);
            Node head1 = LinkedUtil.copyLinked(head);
            Node head2 = LinkedUtil.copyLinked(head);
            Node node = divideLInked(head1, k);
            Node test = test(head2, k);
            Node cur1 = node;
            Node cur2 = test;
            while (cur1 != null && cur2 != null) {
                if (cur1.val != cur2.val) {
                    LinkedUtil.printLinkedList(head);
                    System.out.println("k = " + k);
                    LinkedUtil.printLinkedList(head);
                    System.out.println("出错了...");
                    LinkedUtil.printLinkedList(node);
                    System.out.println("-------");
                    LinkedUtil.printLinkedList(test);
                    return;
                }
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            if (cur1 != null || cur2 != null) {
                LinkedUtil.printLinkedList(head);
                System.out.println("k = " + k);
                LinkedUtil.printLinkedList(head);
                LinkedUtil.printLinkedList(node);
                System.out.println("-------");
                LinkedUtil.printLinkedList(test);
                return;
            }
        }
        System.out.println("测试结束");
    }

    public static Node divideLInked(Node head, int k) {
        if (head == null) {
            return null;
        }
        Node smallHead = null;
        Node small = null;
        Node equals = null;
        Node equalsHead = null;
        Node big = null;
        Node bigHead = null;
        Node temp;
        while (head != null) {
            if (head.val < k) {
                if (small == null) {
                    small = head;
                    smallHead = small;
                } else {
                    small.next = head;
                    small = small.next;
                }
            } else if (head.val == k) {
                if (equals == null) {
                    equals = head;
                    equalsHead = equals;
                } else {
                    equals.next = head;
                    equals = equals.next;
                }
            } else {
                if (big == null) {
                    big = head;
                    bigHead = big;
                } else {
                    big.next = head;
                    big = big.next;
                }
            }
            temp = head;
            head = head.next;
            temp.next = null;
        }
        Node tail;
        if (smallHead != null) {
            head = smallHead;
            tail = small;
            if (equalsHead != null) {
                tail.next = equalsHead;
                tail = equals;
            }
            if (bigHead != null) {
                tail.next = bigHead;
            }
        } else if (equalsHead != null) {
            head = equalsHead;
            tail = equals;
            if (bigHead != null) {
                tail.next = bigHead;
            }
        } else if (bigHead != null) {
            head = bigHead;
        }
        return head;
    }

    public static Node test(Node head, int k) {
        if (head == null) {
            return null;
        }
        List<Node> small = new ArrayList<>();
        List<Node> equals = new ArrayList<>();
        List<Node> big = new ArrayList<>();
        Node node = head;
        while (node != null) {
            if (node.val < k) {
                small.add(node);
            } else if (node.val == k) {
                equals.add(node);
            } else {
                big.add(node);
            }
            node = node.next;
        }
        Node ans = null;
        Node tail = null;
        for (Node n : small) {
            if (ans == null) {
                ans = n;
            }
            if (tail == null) {
                tail = n;
            } else {
                tail.next = n;
                tail = tail.next;
            }
        }
        for (Node n : equals) {
            if (ans == null) {
                ans = n;
            }
            if (tail == null) {
                tail = n;
            } else {
                tail.next = n;
                tail = tail.next;
            }
        }
        for (Node n : big) {
            if (ans == null) {
                ans = n;
            }
            if (tail == null) {
                tail = n;
            } else {
                tail.next = n;
                tail = tail.next;
            }
        }
        if (tail != null) {
            tail.next = null;
        }
        return ans;
    }
}
