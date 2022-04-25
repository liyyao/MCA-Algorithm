package leetcode.tree;

import leetcode.util.TreeUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/path-sum-ii/
 */
public class A03_PathSum_113 {

    public static void main(String[] args) {
        //Integer[] arr = new Integer[] {1,2,3};
        //Integer[] arr = new Integer[] {5,4,8,11,null,13,4,7,2,null,null,5,1};
        Integer[] arr = new Integer[] {1,-2,-3,1,3,-2,null,-1};
        TreeNode root = TreeUtil.buildTree(arr);
        A03_PathSum_113 tree = new A03_PathSum_113();
        List<List<Integer>> lists = tree.pathSum2(root, -1);
        System.out.println(lists);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<Integer> path = new ArrayList<>();
        process2(root, 0, targetSum, path, ans);
        return ans;
    }

    private void process2(TreeNode root, int preSum, int sum, List<Integer> path, List<List<Integer>> ans) {
        if (root.left == null && root.right == null) {
            if (root.val + preSum == sum) {
                path.add(root.val);
                ans.add(copy(path));
                path.remove(path.size() -1);
            }
            return;
        }
        path.add(root.val);
        preSum += root.val;
        if (root.left != null) {
            process2(root.left, preSum, sum, path, ans);
        }
        if (root.right != null) {
            process2(root.right, preSum, sum, path, ans);
        }
        path.remove(path.size() - 1);
    }

    private List<Integer> copy(List<Integer> path) {
        List<Integer> ans = new ArrayList<>(path.size());
        ans.addAll(path);
        return ans;
    }

    /**
     * 自己写的方法，但运行比较慢且占用内存比较大
     * @param root
     * @param targetSum
     * @return
     */
    public List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return Collections.emptyList();
        }
        return process(root, 0, targetSum);
    }

    private List<List<Integer>> process(TreeNode root, int preSum, int sum) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root.left == null && root.right == null) {
            if (root.val + preSum == sum) {
                List<Integer> temp = new LinkedList<>();
                temp.add(root.val);
                ans.add(temp);
            }
            return ans;
        }
        preSum += root.val;
        if (root.left != null) {
            List<List<Integer>> leftAns = process(root.left, preSum, sum);
            if (!leftAns.isEmpty()) {
                for (List<Integer> li : leftAns) {
                    li.add(0, root.val);
                }
                ans.addAll(leftAns);
            }
        }
        if (root.right != null) {
            List<List<Integer>> rightAns = process(root.right, preSum, sum);
            if (!rightAns.isEmpty()) {
                for (List<Integer> ri : rightAns) {
                    ri.add(0, root.val);
                }
                ans.addAll(rightAns);
            }
        }
        return ans;
    }
}
