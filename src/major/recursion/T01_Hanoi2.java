package major.recursion;

/**
 * 打印n层汉诺塔从最左边移动到最右边的全部过程
 */
public class T01_Hanoi2 {

    public static void main(String[] args) {
        int n = 4;
        hanoi(n, "left", "right", "mid");
    }

    public static void hanoi(int n, String from, String to, String other) {
        if (n == 1) {
            System.out.println("move " + n + " from " + from + " to " + to);
            return;
        }
        hanoi(n - 1, from, other, to);
        System.out.println("move " + n + " from " + from + " to " + to);
        hanoi(n - 1, other, to, from);
    }
}


