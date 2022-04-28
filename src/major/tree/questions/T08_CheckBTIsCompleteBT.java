package major.tree.questions;

import common.TreeNode;
import tool.TreeUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 8、判断二叉树是否是完全二叉树
 */
public class T08_CheckBTIsCompleteBT {


    /**
     * 递归套路实现
     * @param
     * @return
     */
    private static class Info {
        boolean isFull;
        boolean isCBT;
        int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    public static boolean check2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isCBT;
    }

    private static Info process(TreeNode root) {
        if (root == null) {
            return new Info(true, true, 0);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        boolean isFull = left.isFull && right.isFull && left.height == right.height;
        boolean isCBT = isFull || left.isFull && right.isFull && left.height == right.height + 1
                || left.isCBT && right.isFull && left.height == right.height + 1
                || left.isFull && right.isCBT && left.height == right.height;
        int height = Math.max(left.height, right.height) + 1;
        return new Info(isFull, isCBT, height);
    }

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
        /*TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n4.left = n6;
        boolean check = check(n1);
        boolean check2 = check2(n1);
        System.out.println(check + "--" + check2);*/

        int testTime = 1000000;
        int maxLevel = 6;
        int maxValue = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            TreeNode node = TreeUtil.generateRandomBST(maxLevel, maxValue);
            if (check(node) != check2(node)) {
                System.out.println("出错了");
                return;
            }
        }
        System.out.println("测试结束");
    }
}
