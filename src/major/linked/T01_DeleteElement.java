package major.linked;

import common.Node;
import tool.LinkedUtil;

/**
 * 把给定的值都删掉
 */
public class T01_DeleteElement {

    public static void main(String[] args) {
        int testTime = 1000000;
        int len = 100;
        int value = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            Node head = LinkedUtil.generateRandomLinkedList(len, value);
            //LinkedUtil.printLinkedList(head);
            int val = (int) (Math.random() * (value + 1));
            //System.out.println("val = " + val);
            Node test = removeValue(head, val);
            Node node = deleteElement(head, val);
            //LinkedUtil.printLinkedList(test);
            //LinkedUtil.printLinkedList(node);
            boolean ans = true;
            if (test == null && node != null || test != null && node == null) {
                ans = false;
                break;
            } else {
                while (test != null && ans) {
                    if (test.val != node.val) {
                        ans = false;
                    } else {
                        test = test.next;
                        node = node.next;
                    }
                }
                if (node != null) {
                    ans = false;
                }
            }
            if (!ans) {
                System.out.println("出错了");
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 自己写的
     * @param head
     * @param val
     * @return
     */
    public static Node deleteElement(Node head, int val) {
        if (head == null) {
            return null;
        }
        Node returnHead = head;
        Node pre = null;
        while (head != null) {
            if (head.val == val) {
                if (pre == null) {
                    returnHead = head.next;
                } else {
                    pre.next = head.next;
                    pre = pre.next;
                }
            } else {
                pre = head;
            }
            head = head.next;
        }
        return returnHead;
    }

    public static Node removeValue(Node head, int num) {
        //head来到第一个不需要删的位置
        while (head != null) {
            if (head.val != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.val == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static Node test(Node head, int val) {
        Node ans = null;
        while (head != null) {
            if (head.val != val) {
                if (ans == null) {
                    ans = head;
                } else {
                    ans.next = head;
                }
            }
            head = head.next;
        }
        return ans;
    }
}
