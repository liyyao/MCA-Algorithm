package major.tree.questions;

import common.TreeNode;
import tool.TreeUtil;

import java.util.*;

/**
 * 15、给定一棵二叉树的头节点head，和另外两个节点a和b。返回a和b的最低公共祖先
 */
public class T15_FindMinCommonParent {

    private static class Info {
        TreeNode node;
        boolean isContain;

        public Info(TreeNode node, boolean isContain) {
            this.node = node;
            this.isContain = isContain;
        }
    }

    public static TreeNode find(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null || node1 == null || node2 == null) {
            return null;
        }
        if (node1 == node2) {
            return node1;
        }
        return process(root, node1, node2).node;
    }

    private static Info process(TreeNode root, TreeNode node1, TreeNode node2) {
        if (root == null) {
            return new Info(null, false);
        }
        Info left = process(root.left, node1, node2);
        Info right = process(root.right, node1, node2);
        if (left.isContain && right.isContain) {
            return new Info(root, true);
        }
        if ((left.isContain || right.isContain) && (root == node1 || root == node2)) {
            return new Info(root, true);
        }
        TreeNode node = left.node != null ? left.node : right.node != null ? right.node : null;
        boolean isContain = left.isContain || right.isContain || root == node1 || root == node2;
        return new Info(node, isContain);
    }

    public static TreeNode test(TreeNode root, TreeNode n1, TreeNode n2) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        map.put(root, null);
        pillParentMap(root, map);
        Set<TreeNode> n1Set = new HashSet<>();
        n1Set.add(null);
        TreeNode cur = n1;
        n1Set.add(cur);
        while (map.get(cur) != null) {
            cur = map.get(cur);
            n1Set.add(cur);
        }
        cur = n2;
        while (!n1Set.contains(cur)) {
            cur = map.get(cur);
        }
        return cur;
    }

    private static void pillParentMap(TreeNode root, Map<TreeNode, TreeNode> map) {
        if (root == null) {
            return;
        }
        pillParentMap(root.left, map);
        pillParentMap(root.right, map);
        if (root.left != null) {
            map.put(root.left, root);
        }
        if (root.right != null) {
            map.put(root.right, root);
        }
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;


        /*TreeNode node = find(n1, n4, n3);
        System.out.println(node == null ? null : node.val);
        TreeNode test = test(n1, n4, n3);
        System.out.println(test == null ? null : test.val);*/

        int testTimes = 1000000;
        int maxLevel = 6;
        int maxValue = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            TreeNode root = TreeUtil.generateRandomBST(maxLevel, maxValue);
            List<TreeNode> list = new ArrayList<>();
            pre(root, list);
            if (list.isEmpty()) {
                continue;
            }
            int size = list.size();
            TreeNode node1 = list.get((int) (Math.random() * size));
            TreeNode node2 = list.get((int) (Math.random() * size));
            TreeNode test = test(root, node1, node2);
            TreeNode find = find(root, node1, node2);
            if (test != find) {
                System.out.println("出错了");
                return;
            }
            if (test != null) {
                System.out.println(test.val + "--" + find.val);
            }
        }
        System.out.println("测试结束");
    }

    private static void pre(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        pre(root.left, list);
        pre(root.right, list);
    }
}
