package tool;

public class BitUtil {


    /**
     * 打印一个数的32位位信息
     * @param data
     */
    public static void printDataBitWise(int data) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((data & (1 << i)) == 0 ? 0 : 1);
        }
        System.out.println();
    }
}
