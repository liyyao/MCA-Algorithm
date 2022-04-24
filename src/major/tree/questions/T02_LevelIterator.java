package major.tree.questions;

import common.TreeNode;
import tool.TreeUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 2、按层遍历二叉树
 */
public class T02_LevelIterator {

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {1,2,3,null,4,null,5,null,null,6};
        TreeNode root = TreeUtil.generateTree(arr);
        level(root);
    }

    public static void level(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + "->");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }
}
