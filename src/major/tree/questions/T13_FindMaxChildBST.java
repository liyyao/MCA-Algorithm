package major.tree.questions;

import common.TreeNode;
import tool.TreeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 13、找到最大的子树是搜索二叉树
 */
public class T13_FindMaxChildBST {

    private static class Info2 {
        int allSize;
        int maxBSTSubTreeSize;
        public int max;
        public int min;

        public Info2(int allSize, int maxBSTSubTreeSize, int max, int min) {
            this.allSize = allSize;
            this.maxBSTSubTreeSize = maxBSTSubTreeSize;
            this.max = max;
            this.min = min;
        }
    }

    public static int maxChildBST2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process2(root).maxBSTSubTreeSize;
    }

    private static Info2 process2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info2 left = process2(root.left);
        Info2 right = process2(root.right);
        int max = root.val;
        int min = root.val;
        int allSize = 1;
        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
            allSize += left.allSize;
        }
        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
            allSize += right.allSize;
        }

        int p1 = -1;
        if (left != null) {
            p1 = left.maxBSTSubTreeSize;
        }

        int p2 = -1;
        if (right != null) {
            p2 = right.maxBSTSubTreeSize;
        }

        int p3 = -1;
        boolean leftIsBST = left == null || (left.maxBSTSubTreeSize == left.allSize);
        boolean rightIsBST = right == null || (right.maxBSTSubTreeSize == right.allSize);
        if (leftIsBST && rightIsBST) {
            boolean leftIsLessRoot = left == null || (left.max < root.val);
            boolean rightIsMoreRoot = right == null || (right.min > root.val);
            if (leftIsLessRoot && rightIsMoreRoot) {
                int leftSize = left == null ? 0 : left.allSize;
                int rightSize = right == null ? 0 : right.allSize;
                p3 = leftSize + rightSize + 1;
            }
        }
        return new Info2(allSize, Math.max(p1, Math.max(p2, p3)), max, min);
    }


    //---------------以下是自己的方法----------------
    private static class Info {
        boolean isBST;
        int size;
        int max;
        int min;

        public Info(boolean isBST, int size) {
            this.isBST = isBST;
            this.size = size;
        }

        public Info(boolean isBST, int size, int max, int min) {
            this.isBST = isBST;
            this.size = size;
            this.max = max;
            this.min = min;
        }
    }

    public static int maxChildBST(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).size;
    }

    private static Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        Info left = process(root.left);
        Info right = process(root.right);
        boolean isBST = true;
        int max = root.val;
        int min = root.val;

        int s1 = 0;
        if (left != null) {
            isBST = left.max < root.val && left.isBST;
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
            s1 = left.size;
        }
        int s2 = 0;
        if (right != null) {
            isBST = isBST && right.min > root.val && right.isBST;
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
            s2 = right.size;
        }
        int s3;
        if (isBST) {
            s3 = s1 + s2 + 1;
        } else {
            s3 = Math.max(s1, s2);
        }
        return new Info(isBST, s3, max, min);
    }

    //-------------test

    public static int test(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int h = getBSTSize(root);
        if (h != 0) {
            return h;
        }
        return Math.max(test(root.left), test(root.right));
    }

    private static int getBSTSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<TreeNode> list = new ArrayList<>();
        in(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).val <= list.get(i - 1).val) {
                return 0;
            }
        }
        return list.size();
    }

    private static void in(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        in(root.left, list);
        list.add(root);
        in(root.right, list);
    }

    public static void main(String[] args) {
        multipleTest();
    }

    private static void multipleTest() {
        int testTime = 1000000;
        int maxValue = 10000;
        int maxLevel = 5;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            TreeNode root = TreeUtil.generateRandomBST(maxLevel, maxValue);
            if (maxChildBST(root) != maxChildBST2(root)) {
                TreeUtil.pre(root);
                System.out.println();
                TreeUtil.in(root);
                System.out.println();
                TreeUtil.pos(root);
                System.out.println("出错了");
                return;
            }
        }
        System.out.println("测试结束");
    }
}
