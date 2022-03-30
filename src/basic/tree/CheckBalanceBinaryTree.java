package basic.tree;

/**
 * 判断是否是平衡二叉树
 */
public class CheckBalanceBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);

        Info process = process(root);
        System.out.println(process);
    }


    public static class Info {
        public boolean isBalance;
        public int height;

        public Info(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "isBalance=" + isBalance +
                    ", height=" + height +
                    '}';
        }
    }

    public static Info process(TreeNode root) {
        if (root == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalance = leftInfo.isBalance && rightInfo.isBalance &&
                Math.abs(leftInfo.height - rightInfo.height) < 2;
        return new Info(isBalance, height);
    }

}
