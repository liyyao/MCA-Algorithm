package basic.tree;

import basic.tree.util.TreeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 利用先序数组和中序数组重建一棵数
 */
public class CreateTree {

    public static void main(String[] args) {
        int[] pre = {1,2,5,9,10,7,8};
        int[] in = {5,10,9,2,7,1,8};
        TreeNode treeNode = buildTree(pre, in);
        TreeUtil.pre(treeNode);
        System.out.println("=========");
        TreeUtil.in(treeNode);
        System.out.println("=========");
        TreeUtil.pos(treeNode);
        System.out.println("=========");
    }


    public static TreeNode buildTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        Map<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            valueIndexMap.put(in[i], i);
        }
        return createNode2(pre, 0, pre.length -1, in, 0, in.length - 1, valueIndexMap);
    }

    public static TreeNode createNode(int[] pre, int l1, int r1, int[] in, int l2, int r2) {
        if (l1 > r1) {
            return null;
        }
        TreeNode root = new TreeNode(pre[l1]);
        if (l1 == r1) {
            return root;
        }
        int find = l2;
        while (in[find] != pre[l1]) {
            find++;
        }
        TreeNode left = createNode(pre, l1 + 1, find - l2 + l1, in, l2, find - 1);
        TreeNode right = createNode(pre, find - l2 + l1 + 1, r1, in, find + 1, r2);
        root.left = left;
        root.right = right;
        return root;
    }

    /**
     * 优化查找过程
     * @param pre
     * @param l1
     * @param r1
     * @param in
     * @param l2
     * @param r2
     * @return
     */
    public static TreeNode createNode2(int[] pre, int l1, int r1, int[] in, int l2, int r2, Map<Integer, Integer> valueIndexMap) {
        if (l1 > r1) {
            return null;
        }
        TreeNode root = new TreeNode(pre[l1]);
        if (l1 == r1) {
            return root;
        }
        int find = valueIndexMap.get(pre[l1]);
        TreeNode left = createNode2(pre, l1 + 1, find - l2 + l1, in, l2, find - 1, valueIndexMap);
        TreeNode right = createNode2(pre, find - l2 + l1 + 1, r1, in, find + 1, r2, valueIndexMap);
        root.left = left;
        root.right = right;
        return root;
    }


}
