package major.recursion;

/**
 * 打印n层汉诺塔从最左边移动到最右边的全部过程
 */
public class T01_Hanoi {

    public static void main(String[] args) {
        left2Right(4);
    }

    public static void left2Right(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to right");
            return;
        }
        left2Mid(n - 1);
        System.out.println("move " + n + " from left to right");
        mid2Right(n - 1);
    }

    public static void left2Mid(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to mid");
            return;
        }
        left2Right(n - 1);
        System.out.println("move " + n + " from left to mid");
        right2Mid(n - 1);
    }

    public static void mid2Right(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to right");
            return;
        }
        mid2Left(n - 1);
        System.out.println("move " + n + " from mid to right");
        left2Right(n - 1);
    }

    public static void mid2Left(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to left");
            return;
        }
        mid2Right(n - 1);
        System.out.println("move " + n + " from mid to left");
        right2Left(n - 1);
    }

    public static void right2Left(int n) {
        if (n == 1) {
            System.out.println("move 1 from right to left");
            return;
        }
        right2Mid(n - 1);
        System.out.println("move " + n + " from right to left");
        mid2Left(n - 1);
    }

    public static void right2Mid(int n) {
        if (n == 1) {
            System.out.println("move 1 from right to mid");
            return;
        }
        right2Left(n - 1);
        System.out.println("move " + n + " from right 2 mid");
        left2Mid(n - 1);
    }
}


