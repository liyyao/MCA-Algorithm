package basic.tree;

/**
 * 镜面二叉树
 */
public class MirrorTree {
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static boolean MirrorTree(TreeNode head) {
        return isMirror(head, head);
    }

    public static boolean isMirror(TreeNode head1, TreeNode head2) {
        if (head1 == null ^ head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        return head1.val == head2.val && isMirror(head1.left, head2.right) && isMirror(head1.right, head2.left);
    }
}
