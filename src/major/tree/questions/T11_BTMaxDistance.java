package major.tree.questions;

import common.TreeNode;

/**
 * 11、给定一棵二叉树的头节点head,任何两个节点之间都存在距离，返回整棵二叉树的最大距离
 */
public class T11_BTMaxDistance {

    private static class Info {
        int height;
        int maxDistance;

        public Info(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    public static int maxDistance(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).maxDistance;
    }
    
    private static Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        int height = Math.max(left.height, right.height) + 1;
        int maxDistance = left.height + right.height + 1;
        maxDistance = Math.max(left.maxDistance, maxDistance);
        maxDistance = Math.max(right.maxDistance, maxDistance);
        return new Info(height, maxDistance);
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
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n4.left = n6;
        n5.left = n7;
        //n5.right = n8;
        n6.left = n9;
        n8.left = n10;
        int distance = maxDistance(n1);
        System.out.println(distance);
    }
}
