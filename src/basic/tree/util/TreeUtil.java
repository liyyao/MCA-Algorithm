package basic.tree.util;

import basic.tree.TreeNode;

public class TreeUtil {

    /**
     * 先序遍历
     * @param head
     */
    public static void pre(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + "->");
        pre(head.left);
        pre(head.right);
    }

    /**
     * 中序遍历
     * @param head
     */
    public static void in(TreeNode head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.print(head.val + "->");
        in(head.right);
    }

    public static void pos(TreeNode head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.print(head.val + "->");
    }
}
