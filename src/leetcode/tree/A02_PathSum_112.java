package leetcode.tree;

import leetcode.util.TreeUtil;

/**
 * https://leetcode.com/problems/path-sum/
 */
public class A02_PathSum_112 {

    public static void main(String[] args) {
        //Integer[] arr = new Integer[] {3,9,20,null,null,15,7};
        //Integer[] arr = new Integer[] {1,2,3,4,null,null,5};
        Integer[] arr = new Integer[] {1,-2,-3,1,3,-2,null,-1};
        TreeNode root = TreeUtil.buildTree(arr);
        A02_PathSum_112 tree = new A02_PathSum_112();
        boolean ans = tree.hasPathSum(root, -1);
        System.out.println(ans);
    }

    /**
     * 别人的方法
     */
    public static boolean isSum = false;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        isSum = false;
        process(root, 0, targetSum);
        return isSum;
    }

    private void process(TreeNode root, int preSum, int sum) {
        if (root.left == null && root.right == null) {
            if (root.val + preSum == sum) {
                isSum = true;
            }
            return;
        }
        preSum += root.val;
        if (root.left != null) {
            process(root.left, preSum, sum);
        }
        if (root.right != null) {
            process(root.right, preSum, sum);
        }
    }


    /**
     * 自己写的方法
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            //System.out.println(root.val);
            return root.val == targetSum;
        }
        boolean leftAns;
        boolean rightAns;
        if (root.left != null) {
            root.left.val = root.val + root.left.val;
            leftAns = hasPathSum(root.left, targetSum);
        } else {
            leftAns = false;
        }
        if (root.right != null) {
            root.right.val = root.val + root.right.val;
            rightAns = hasPathSum(root.right, targetSum);
        } else {
            rightAns = false;
        }
        return leftAns || rightAns;
    }
}
