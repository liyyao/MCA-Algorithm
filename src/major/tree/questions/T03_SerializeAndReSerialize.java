package major.tree.questions;

import common.TreeNode;
import tool.TreeUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树序列化和反序列化
 */
public class T03_SerializeAndReSerialize {

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {1,2,3,null,4,null,5,null,null,6};
        TreeNode root = TreeUtil.generateTree(arr);
        String val = levelSerialize(root);
        System.out.println(val);
        TreeNode treeNode = levelReSerialize(val);
        TreeUtil.level(treeNode);
    }

    public static String preSerialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append(",");
        sb.append(preSerialize(root.left)).append(",");
        sb.append(preSerialize(root.right)).append(",");
        return sb.length() == 0 ? null : sb.substring(0, sb.length() - 1);
    }

    public static TreeNode preReSerialize(String str) {
        Queue<TreeNode> queue = parseTreeNode(str);
        if (queue == null) return null;
        return preReSerialize(queue);
    }

    private static Queue<TreeNode> parseTreeNode(String str) {
        if (str == null) {
            return null;
        }
        String[] nodeArr = str.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        for (String node : nodeArr) {
            if (node != null && !"null".equals(node)) {
                TreeNode temp = new TreeNode(Integer.parseInt(node));
                queue.add(temp);
            } else  {
                queue.add(null);
            }
        }
        return queue;
    }

    private static TreeNode preReSerialize(Queue<TreeNode> queue) {
        TreeNode node = queue.poll();
        if (node == null) {
            return null;
        }
        node.left = preReSerialize(queue);
        node.right = preReSerialize(queue);
        return node;
    }

    public static String levelSerialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                sb.append("null,");
                continue;
            }
            sb.append(node.val).append(",");
            queue.add(node.left);
            queue.add(node.right);
        }
        return sb.length() == 0 ? null : sb.substring(0, sb.length() - 1);
    }

    public static TreeNode levelReSerialize(String str) {
        Queue<TreeNode> queue = parseTreeNode(str);
        if (queue == null || queue.peek() == null) {
            return null;
        }
        Queue<TreeNode> help = new LinkedList<>();
        TreeNode root = queue.poll();
        help.add(root);
        while (!help.isEmpty()) {
            TreeNode node = help.poll();
            node.left = queue.poll();
            node.right = queue.poll();
            if (node.left != null) {
                help.add(node.left);
            }
            if (node.right != null) {
                help.add(node.right);
            }
        }
        return root;
    }
}
