package basic.linked;

/**
 * 双向链表反转
 */
public class DoubleLinked {

    public static void main(String[] args) {
        DoubleNode node = new DoubleNode(1);
        node.next = new DoubleNode(2);
        node.next.pre = node; //第2个节点的上一个节点是1
        node.next.next = new DoubleNode(3); //第2个节点的下一个节点是3
        node.next.next.pre = node.next;     //第3个节点的上一个节点是2
        node.next.next.next = new DoubleNode(4);    //第3个节点的下一个节点是4
        node.next.next.next.pre = node.next.next;  //第4个节点的上一个节点是3
        print(node);
        DoubleNode doubleNode = reverseDoubleNodeLinked(node);
        print(doubleNode);
    }

    public static DoubleNode reverseDoubleNodeLinked(DoubleNode head) {
        DoubleNode next = null;
        while(head != null) {
            head.pre = head.next;
            head.next = next;
            next = head;
            head = head.pre;
        }
        return next;
    }

    public static void print(DoubleNode node) {
        DoubleNode node1 = null;
        while (node != null) {
            System.out.print(node.value + "->");
            node1 = node;
            node = node.next;
        }
        System.out.print("null");
        System.out.println();

        while (node1 != null) {
            System.out.print(node1.value + "->");
            node1 = node1.pre;
        }
        System.out.print("null");
        System.out.println();
    }
}

class DoubleNode {

    public DoubleNode(int value) {
        this.value = value;
    }

    DoubleNode pre;
    DoubleNode next;
    int value;
}
