package major.tree.questions;

import common.TreeNode;
import tool.TreeUtil;

import java.util.Stack;

/**
 * 1、使用非递归的方式实现先序、中序、后序遍历
 */
public class T01_TreeIterator {

    public static void main(String[] args) {
        //Integer[] arr = new Integer[]{1, 2, 3, null, 4, 5, null, 7};
        Integer[] arr = new Integer[]{1, 2, 3, null, 4, 5, 6, 7};
        TreeNode root = TreeUtil.generateTree(arr);
        in2(root);
        TreeUtil.in(root);
        System.out.println();
    }

    /**
     * 先序遍历-无递归
     * @param root
     */
    private static void pre(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + "->");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    private static void in2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.print(root.val + "->");
                root = root.right;
            }
        }
        System.out.println();
    }

    /**
     * 中序遍历-无递归
     * @param root
     */
    private static void in(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        boolean rootFlag = false;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                rootFlag = true;
                continue;
            }
            if (node.left == null && node.right == null) {
                rootFlag = true;
                System.out.print(node.val + "->");
                continue;
            }
            if (rootFlag) {
                System.out.print(node.val + "->");
                rootFlag = false;
                continue;
            }
            if (node.right != null) {
                stack.push(node.right);
            } else {
                stack.push(null);
            }
            if (node.left == null) {
                System.out.print(node.val + "->");
                continue;
            }
            stack.push(node);
            stack.push(node.left);
        }
        System.out.println();
    }

    /**
     * 后序遍历-无递归
     * @param root
     */
    private static void pos(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode treeNode = stack1.pop();
            if (treeNode.left != null) {
                stack1.push(treeNode.left);
            }
            if (treeNode.right != null) {
                stack1.push(treeNode.right);
            }
            stack2.push(treeNode);
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + "->");
        }
        System.out.println();
    }

    /**
     * 只用一个栈
     * @param root
     */
    private static void pos2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode c = null;
        while (!stack.isEmpty()) {
            c = stack.pop();
            if (c.left != null && root != c.left && root != c.right) {
                stack.push(c.left);
            } else if (c.right != null && root != c.right) {
                stack.push(c.right);
            } else {
                System.out.println(stack.pop() + "->");
                root = c;
            }
        }
    }
}
