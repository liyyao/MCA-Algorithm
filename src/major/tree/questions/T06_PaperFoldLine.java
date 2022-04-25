package major.tree.questions;

/**
 * 6、请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，
 * 即抓痕突起的方向指向纸条的背面。如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，
 * 从上到下依次是下折痕、下折痕、和上折痕。给定一个输入参数N，代表纸条都从下边向上方连续对折N次。请
 * 从上到下打印所有折痕的方向。
 * 例如：N=1时，打印：down；N=2时，打印：down down up
 *
 * 分析：这道题的折痕规律是：第1次折痕是down，第2次折痕在第1次折痕上下，上面是down，下面是up
 * 第3次折痕，分别在第2次折痕的上下，在2-down上面的折痕是down,在2-down下面的折痕是up
 * 在2-up上面的折痕是down，在2-up下面的折痕是down
 * 上面这个过程可以看作是一个建立二叉树的过程，根节点是down，然后所有的左孩子是down,右孩子是up
 * 折痕从上到下就是这棵二叉树的中序遍历
 */
public class T06_PaperFoldLine {

    public static void main(String[] args) {
        printPaperFoldLine(2);
    }

    public static void printPaperFoldLine(int N) {
        process(1, N, true);
    }

    /**
     * 当前你来了一个节点，脑海中想象的！
     * 这个节点在第level层，一共有N层，N固定不变
     * 这个节点如果是凹的话，down = T
     * 这个节点如果是凸的话，down = F
     * 函数的功能：中序打印以你想象的节点为头的整棵树！
     * @param level
     * @param N
     * @param down
     */
    private static void process(int level, int N, boolean down) {
        if (level > N) {
            return;
        }
        process(level + 1, N, true);
        System.out.println(down ? "down" : "up");
        process(level + 1, N, false);
    }
}
