package major.tree.questions;

import common.TreeNode;

/**
 *
 */
public class T12_CheckBTIsFullBT {

    public static boolean isFullBT(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isFull;
    }

    private static class Info {
        boolean isFull;
        int height;

        public Info(boolean isFull, int height) {
            this.isFull = isFull;
            this.height = height;
        }
    }

    private static Info process(TreeNode root) {
        if (root == null) {
            return new Info(true, 0);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        boolean isFull = left.height == right.height && left.isFull && right.isFull;
        int height = Math.max(left.height, right.height) + 1;
        return new Info(isFull, height);
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n6.left = n8;
        boolean fullBT = isFullBT(n1);
        System.out.println(fullBT);
    }
}
