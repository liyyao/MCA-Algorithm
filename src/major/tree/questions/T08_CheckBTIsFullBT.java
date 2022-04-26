package major.tree.questions;

import common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 8、判断二叉树是否是完全二叉树
 */
public class T08_CheckBTIsFullBT {

    /**
     * 非递归
     * @param root
     * @return
     */
    public static boolean check(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isLast = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (isLast && (node.left != null || node.right != null)
                    || node.left == null && node.right != null) {
                return false;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            if (node.left == null || node.right == null) {
                isLast = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n1.left = n2;
        n1.right = n3;
        //n2.left = n4;
        //n2.right = n5;
        //n4.left = n6;
        boolean check = check(n1);
        System.out.println(check);
    }
}
