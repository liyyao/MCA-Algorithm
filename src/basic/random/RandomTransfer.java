package basic.random;

/**
 * 从1~5随机变换成1~7随机
 * 意思就是有一个函数，给出1~5这5个数的概率是相同的，
 * 只借助这一个函数，现在实现一个函数，给出1~7这7个数的概率是相同的。
 *
 * 推广到从a~b随机变换到c~d随机
 * 1.生成一个01随机器：如果a~b是偶数，则前一半返回0，后一半返回1；如果a~b是奇数，用中间数重做；
 * 2.求出二进制的位数N：用d-c，求出0~(d-c)的范围，然后计算需要几个二进制位可以表示
 * 3.得出2的N-1次方数的随机器：用第1步生成的01随机生成器做N次位移并相加即可
 * 4.用第3步生成的随机器做判断再+c可生成一个以c~d的随机器
 */
public class RandomTransfer {

    public static void main(String[] args) {
        int testTimes = 700000;
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
     * 借助f()函数，生成一个01随机器
     * 即该函数0~1随机
     * @return
     */
    public static int f2() {
        int ans;
        do {
            ans = f();
        } while (ans == 3);
        return ans < 3 ? 0 : 1;
    }

    /**
     * 返回一个二进制 000~111随机数
     * 即返回一个十进制0~7的随机数
     * @return
     */
    public static int f3() {
        return (f2() << 2) + (f2() <<1) + f2();
    }

    /**
     * 返回一个0~6上的随机
     * @return
     */
    public static int f4() {
        int ans;
        do {
            ans = f3();
        } while (ans == 7);
        return ans;
    }

    /**
     * 返回一个1~7上的随机
     * @return
     */
    public static int g() {
        return f4() + 1;
    }
}
