package basic;

/**
 * 位运算
 * 打印一个整数的32位二进制
 */
public class BitWise {

    public static void main(String[] args) {
        int a = Integer.MAX_VALUE;
        print(a);
    }

    public static void print(int num) {
        for (int i = 31; i >=0; i--) {
            System.out.print((num & (1 << i)) == 0 ? 0 : 1);
        }
        System.out.println();
    }
}
