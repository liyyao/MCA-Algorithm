package major.tree.questions;

import common.TreeNode;
import tool.TreeUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class T05_BT_MaxWidth {

    /**
     * 这个方法只借助了queue和几个有限的变量，没有借助queue的size
     * @param root
     * @return
     */
    public static int maxWidth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxWidth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode currentEnd = root;
        TreeNode nextEnd = null;
        int count = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
                nextEnd = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextEnd = node.right;
            }
            count++;
            if (currentEnd == node) {
                currentEnd = nextEnd;
                maxWidth = Math.max(maxWidth, count);
                count = 0;
            }
        }
        return maxWidth;
    }

    /**
     * 下面是自己写的方法，但是用了queue的size
     * @param root
     * @return
     */
    public static int maxWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxWidth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            maxWidth = Math.max(maxWidth, size);
            while (size != 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
        }
        return maxWidth;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxLevel = 100;
        int maxValue = 500;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int level = (int) (Math.random() * maxLevel + 1);
            TreeNode root = TreeUtil.generateRandomBST(level, maxValue);
            int ans1 = maxWidth(root);
            int ans2 = maxWidth2(root);
            if (ans1 != ans2) {
                System.out.println("出错了");
                return;
            }
        }
        System.out.println("测试结束");
    }
}
