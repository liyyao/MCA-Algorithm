package leetcode.util;


import leetcode.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

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

    /**
     * 创建二叉树
     * @param arr
     * @return
     */
    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(arr[0]);
        if (arr.length == 1) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for (int i = 1; i < arr.length;) {
            TreeNode node = queue.poll();
            if (arr[i] != null) {
                node.left = new TreeNode(arr[i]);
                queue.add(node.left);
            }
            if ((i + 1) < arr.length && arr[i + 1] != null) {
                node.right = new TreeNode(arr[i + 1]);
                queue.add(node.right);
            }
            i += 2;
        }
        return root;
    }
}
