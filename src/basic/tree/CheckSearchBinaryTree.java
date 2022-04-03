package basic.tree;

/**
 * 判断是否是搜索二叉树
 * 两种方案
 * 1.中序遍历二叉树，得到的结果是升序的 就是搜索二叉树
 * 2.递归
 */
public class CheckSearchBinaryTree {

    public static boolean isSearchBinaryTree(TreeNode root) {

        return false;
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info left = process(root.left);
        Info right = process(root.right);
        int max = root.val;
        int min = root.val;
        if (left != null) {
            max = Math.max(left.max, max);
            min = Math.min(left.min, min);
        }
        if (right != null) {
            max = Math.max(right.max, max);
            min = Math.min(right.min, min);
        }
        boolean sbt = true;
        if (left != null && !left.isSBT) {
            sbt = false;
        }
        if (right != null && !right.isSBT) {
            sbt = false;
        }
        boolean leftMaxLessRoot = left == null ? true : left.max < root.val;
        boolean rightMinMoreRoot = right == null ? true : right.min < root.val;
        if (!leftMaxLessRoot || !rightMinMoreRoot) {
            sbt = false;
        }
        return new Info(sbt, max, min);
    }

    public static class Info {
        public boolean isSBT;
        public int max;
        public int min;

        public Info(boolean isSBT, int max, int min) {
            this.isSBT = isSBT;
            this.max = max;
            this.min = min;
        }
    }

}
