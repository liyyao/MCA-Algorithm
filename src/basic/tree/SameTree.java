package basic.tree;

/**
 * 判断两个树是否相同
 */
public class SameTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }


    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null ^ q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);

        TreeNode head2 = new TreeNode(1);
        head2.left = new TreeNode(2);
        head2.right = new TreeNode(3);
        head2.left.left = new TreeNode(4);
        head2.left.right = new TreeNode(5);
        head2.right.left = new TreeNode(6);
        head2.right.right = new TreeNode(8);

        boolean sameTree = isSameTree(head, head2);
        System.out.println(sameTree);
    }
}
