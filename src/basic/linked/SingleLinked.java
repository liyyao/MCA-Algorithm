package basic.linked;

/**
 * 单链表反转
 */
public class SingleLinked {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        node1.next = new Node(2);
        node1.next.next = new Node(3);
        node1.next.next.next = new Node(4);
        print(node1);
        Node node2 = reverseLinked(node1);
        print(node2);
    }

    public static void print(Node node) {
        while (node != null) {
            System.out.print(node.value + "->");
            node = node.next;
        }
        System.out.print("null");
        System.out.println();
    }

    public static Node reverseLinked(Node head) {
        Node pre = null;
        Node next;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

}

class Node {
    public Node(int value) {
        this.value = value;
    }
    
    Node next;
    int value;
}
