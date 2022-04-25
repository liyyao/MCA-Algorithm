package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/encode-n-ary-tree-to-binary-tree/
 * 431.Encode N-ary Tree to Binary Tree
 * 将一个多叉树转成一个二叉树，且转成的二叉树要能还原成多叉树
 */
public class A04_EncodeNaryTree2BinaryTree {

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            this.val= x;
        }
    }

    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode head = new TreeNode(root.val);
        head.left = en(root.children);
        return head;
    }

    private TreeNode en(List<Node> children) {
        if (children == null) {
            return null;
        }
        TreeNode head = null;
        TreeNode cur = null;
        for (Node child : children) {
            TreeNode tNode = new TreeNode(child.val);
            if (head == null) {
                head = tNode;
            } else {
                cur.right = tNode;
            }
            cur = tNode;
            cur.left = en(child.children);
        }
        return head;
    }

    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        return new Node(root.val, de(root.left));
    }

    public List<Node> de(TreeNode root) {
        List<Node> children = new ArrayList<>();
        while (root != null) {
            Node cur = new Node(root.val, de(root.left));
            children.add(cur);
            root = root.right;
        }
        return children;
    }


    //-----------------下面是自己的方法，写的比较挫，还是对递归不熟悉-----------------------------
    public TreeNode encode2(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode treeRoot = new TreeNode(root.val);
        if (root.children == null || root.children.isEmpty()) {
            return treeRoot;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.add(treeRoot);
        while (!queue.isEmpty()) {
            TreeNode treeNode = treeNodeQueue.poll();
            Node node = queue.poll();
            if (node.children != null && !node.children.isEmpty()) {
                queue.addAll(node.children);
            }
            TreeNode en = en(node);
            if (en == null) {
                continue;
            }
            treeNode.left = en;
            while (en != null) {
                treeNodeQueue.add(en);
                en = en.right;
            }
        }
        return treeRoot;
    }

    private TreeNode en(Node root) {
        if (root == null || root.children == null || root.children.isEmpty()) {
            return null;
        }
        TreeNode left = new TreeNode(root.children.get(0).val);
        TreeNode cur = left;
        for (int i = 1; i < root.children.size(); i++) {
            TreeNode right = new TreeNode(root.children.get(i).val);
            cur.right = right;
            cur = right;
        }
        return left;
    }

    public Node decode2(TreeNode root) {
        if (root == null) {
            return null;
        }
        return de(root.val, root.left);
    }

    private static Node de(int val, TreeNode node) {
        Node root = new Node(val);
        if (node == null) {
            return root;
        }
        List<Node> children = new ArrayList<>();
        while (node != null) {
            Node de = de(node.val, node.left);
            children.add(de);
            node = node.right;
        }
        root.children = children;
        return root;
    }

    public static void pre(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val + "->");
        pre(head.left);
        pre(head.right);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node root2 = new Node(2);
        Node root3 = new Node(3);
        Node root4 = new Node(4);
        Node root5 = new Node(5);
        Node root6 = new Node(6);
        List<Node> list1 = new ArrayList<>();
        list1.add(root3);
        list1.add(root2);
        list1.add(root4);
        List<Node> list2 = new ArrayList<>();
        list2.add(root5);
        list2.add(root6);
        root.children = list1;
        root3.children = list2;
        A04_EncodeNaryTree2BinaryTree e = new A04_EncodeNaryTree2BinaryTree();
        TreeNode encode = e.encode(root);
        pre(encode);
        System.out.println();
        Node decode = e.decode(encode);
        Queue<Node> queue = new LinkedList<>();
        queue.add(decode);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.val + "->");
            if (node.children != null && !node.children.isEmpty()) {
                queue.addAll(node.children);
            }
        }
    }
}
