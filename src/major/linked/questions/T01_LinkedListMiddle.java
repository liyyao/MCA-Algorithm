package major.linked.questions;

import common.Node;
import tool.LinkedUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目1：
 * 给定一个单链表，使用有限几个变量，实现以下要求
 * 1、输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * 2、输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 * 3、输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 * 4、输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 */
public class T01_LinkedListMiddle {

    public static Node getMiddle4(Node root) {
        Node slow = root;
        Node fast = root;
        Node preStepOneNode = null;
        while (fast != null) {
            if (fast.next == null) {
                return preStepOneNode;
            }
            if (fast.next.next == null) {
                return slow;
            }
            preStepOneNode = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return preStepOneNode;
    }

    public static Node midOrDownMidPreNode(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        if (head.next.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 3、输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     * @param root
     * @return
     */
    public static Node getMiddle3(Node root) {
        Node slow = root;
        Node fast = root;
        Node preStepOneNode = null;
        while (fast != null) {
            if (fast.next == null || fast.next.next == null) {
                return preStepOneNode;
            }
            preStepOneNode = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return preStepOneNode;
    }

    public static Node midOrUpMidPreNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 2、输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     * @param root
     * @return
     */
    public static Node getMiddle2(Node root) {
        Node slow = root;
        Node fast = root;
        while (fast != null) {
            if (fast.next == null) {
                return slow;
            }
            if (fast.next.next == null) {
                return slow.next;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node midOrDownMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 1、输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     * @param root
     * @return
     */
    public static Node getMiddle1(Node root) {
        Node slow = root;
        Node fast = root;
        while (fast != null) {
            if (fast.next == null || fast.next.next == null) {
                return slow;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node midOrUpMidNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 用于测试
     * @param root
     * @param state 1-返回上中点；2-返回下中点；3-返回上中点前一个；4-返回下中点前一个
     * @return
     */
    public static Node test(Node root, int state) {
        if (root == null) {
            return null;
        }
        List<Node> list = new ArrayList<>();
        Node node = root;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        if (state == 1) {
            return list.get((list.size() - 1) / 2);
        }
        if (state == 2) {
            return list.get(list.size() / 2);
        }
        if (state == 3) {
            int i = (list.size() - 1) / 2 - 1;
            if (i < 0) {
                return null;
            }
            return list.get(i);
        } else {
            int i = list.size() / 2 - 1;
            if (i < 0) {
                return null;
            }
            return list.get(i);
        }
    }

    public static void main(String[] args) {
        int testTime = 10000000;
        int len = 100;
        int value = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node root = LinkedUtil.generateRandomLinkedList(len, value);
            if (Math.random() < 0.25) {
                Node test = test(root, 1);
                Node middle1 = getMiddle1(root);
                Node mid1 = midOrUpMidNode(root);
                if (test != middle1 || middle1 != mid1) {
                    LinkedUtil.printLinkedList(root);
                    System.out.println("getMiddle1 出错了...");
                    System.out.println(middle1.val + "--" + mid1.val);
                    return;
                }
            } else if (Math.random() < 0.5) {
                Node test = test(root, 2);
                Node middle2 = getMiddle2(root);
                Node mid2 = midOrDownMidNode(root);
                if (test != middle2 || middle2 != mid2) {
                    System.out.println("getMiddle2 出错了...");
                    return;
                }
            } else if (Math.random() < 0.75) {
                Node test = test(root, 3);
                Node middle3 = getMiddle3(root);
                Node mid3 = midOrUpMidPreNode(root);
                if (test != middle3 || middle3 != mid3) {
                    System.out.println("getMiddle3 出错了...");
                    return;
                }
            } else {
                Node test = test(root, 4);
                Node middle4 = getMiddle4(root);
                Node mid4 = midOrDownMidPreNode(root);
                if (test != middle4 || middle4 != mid4) {
                    System.out.println("getMiddle4 出错了...");
                    return;
                }
            }
        }
        System.out.println("测试结束");
    }
}
