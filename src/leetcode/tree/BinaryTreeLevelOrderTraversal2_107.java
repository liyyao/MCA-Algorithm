package leetcode.tree;


import leetcode.util.TreeUtil;

import java.util.*;

public class BinaryTreeLevelOrderTraversal2_107 {

    public static void main(String[] args) {
        //Integer[] arr = new Integer[] {3,9,20,null,null,15,7};
        Integer[] arr = new Integer[] {1,2,3,4,null,null,5};
        TreeNode node = TreeUtil.buildTree(arr);
        TreeUtil.pre(node);
        System.out.println("=====");
        TreeUtil.in(node);
        System.out.println("=====");
        TreeUtil.pos(node);
        System.out.println("=====");
        BinaryTreeLevelOrderTraversal2_107 tree = new BinaryTreeLevelOrderTraversal2_107();
        List<List<Integer>> lists = tree.levelOrderBottom2(node);
        System.out.println(lists.toString());
    }

    /**
     * 这个方法使用的队列，比自己写的方法快很多
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if (!temp.isEmpty()) {
                ans.add(0, temp);
            }
        }
        return ans;
    }

    /**
     * 我的方法
     * 这么复杂是因为不熟悉linked.add(int index, E e)；这个方法
     * 这个方法是将元素插入到指定位置，且将指定位置已有的原素及后面的元素向后移
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new LinkedList<>();
        Stack<List<TreeNode>> stack = new Stack<>();
        Stack<List<Integer>> valueStack = new Stack<>();
        List<TreeNode> rootList = new ArrayList<>();
        List<Integer> rootValue = new ArrayList<>();
        rootList.add(root);
        rootValue.add(root.val);
        stack.push(rootList);
        valueStack.push(rootValue);
        while (!stack.isEmpty()) {
            List<TreeNode> nodes = stack.pop();
            List<TreeNode> temp = new ArrayList<>();
            List<Integer> value = new ArrayList<>();
            for (TreeNode node : nodes) {
                if (node.left != null) {
                    temp.add(node.left);
                    value.add(node.left.val);
                }
                if (node.right != null) {
                    temp.add(node.right);
                    value.add(node.right.val);
                }
            }
            if (!temp.isEmpty()) {
                stack.push(temp);
                valueStack.push(value);
            }
        }
        while (!valueStack.isEmpty()) {
            result.add(valueStack.pop());
        }
        return result;
    }
}
