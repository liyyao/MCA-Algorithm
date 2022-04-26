package major.tree.questions;

/**
 * 7、二叉树结构如下定义：
 * Class Node{
 *     V value;
 *     Node left;
 *     Node right;
 *     Node parent;
 * }
 * 给你二叉树中的某个节点，返回该节点的后继节点。
 */
public class T07_BTSucceedNode<V> {

    private static class Node<V> {
        V value;
        Node<V> left;
        Node<V> right;
        Node<V> parent;

        public Node(V value) {
            this.value = value;
        }
    }

    public Node<V> findSucceedNode(Node<V> node) {
        if (node == null) {
            return null;
        }
        Node<V> cur = null;
        if (node.right != null) {
            cur = node.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }
        cur = node.parent;
        while (cur != null && cur.right == node) {
            node = cur;
            cur = cur.parent;
        }
        return cur;
    }

    public static void main(String[] args) {
        Node<Integer> n1 = new Node<>(1);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n4 = new Node<>(4);
        Node<Integer> n5 = new Node<>(5);
        Node<Integer> n6 = new Node<>(6);
        Node<Integer> n7 = new Node<>(7);
        Node<Integer> n8 = new Node<>(8);
        n1.left = n2;
        n1.right = n4;
        n2.left = n3;
        n2.right = n5;
        n2.parent = n1;
        n3.parent = n2;
        n4.left = n6;
        n4.parent = n1;
        n5.left = n8;
        n5.parent = n2;
        n6.right = n7;
        n6.parent = n4;
        n7.parent = n6;
        n8.parent = n5;
        T07_BTSucceedNode<Integer> s = new T07_BTSucceedNode<>();
        Node<Integer> succeedNode = s.findSucceedNode(n4);
        System.out.println(succeedNode == null ? null : succeedNode.value);
    }
}
