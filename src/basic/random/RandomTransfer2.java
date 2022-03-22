package basic.random;

/**
 * 从1~5随机变换成1~7随机
 * 意思就是有一个函数，给出1~5这5个数的概率是相同的，
 * 只借助这一个函数，现在实现一个函数，给出1~7这7个数的概率是相同的。
 */
public class RandomTransfer2 {

    public static void main(String[] args) {
        int testTimes = 100000;
        int arr[] = new int[8];
        for (int i = 0; i < testTimes; i++) {
            arr[g()]++;
        }
        for (int i = 0; i < 8; i++) {
            System.out.println("arr[" + i + "] 出现的次数为 " + arr[i]);
        }
    }

    /**
     * 给出1~5随机 不能改
     * @return 1~5数字
     */
    public static int f() {
        return (int) (Math.random() * 5) + 1;
    }

    /**
     * 返回一个1~7上的随机
     * @return 1~7数字
     */
    public static int g() {
        //todo
        return 0;
    }
}
