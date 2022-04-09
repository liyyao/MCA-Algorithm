package major.xor;

/**
 * 2.一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
 */
public class EvenTimesOddTimes {

    public static void main(String[] args) {
        int[] arr = new int[] {1,1,3,3,4};
        printOddTimesNum(arr);
    }

    public static void printOddTimesNum(int[] arr) {
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
        }
        System.out.println(xor);
    }
}
