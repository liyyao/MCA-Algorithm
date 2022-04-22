package tool;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtil {

    public static TreeNode generateTree(Integer[] arr) {
        if (arr.length == 0 || arr[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for (int i = 1; i < arr.length; i = i + 2) {
            if (queue.isEmpty()) {
                return root;
            }
            TreeNode treeNode = queue.poll();
            Integer leftData = arr[i];
            if (leftData != null) {
                TreeNode left = new TreeNode(leftData);
                treeNode.left = left;
                queue.add(left);
            }
            if ((i + 1 < arr.length) && (arr[i + 1] != null)) {
                TreeNode right = new TreeNode(arr[i + 1]);
                treeNode.right = right;
                queue.add(right);
            }
        }
        return root;
    }

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

    /**
     * 后序遍历
     * @param head
     */
    public static void pos(TreeNode head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.print(head.val + "->");
    }

}
