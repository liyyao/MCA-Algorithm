package basic.random;

/**
 * 01不等概率随机到01等概率随机
 * 意思就是有一个函数f()，得出0的概率是p，得出1的概率是1-p，且p不等于0.5
 * 现在只借助函数f()，实现一个函数g()，使得得出的01概率是相等的
 */
public class RandomTransferZeroAndOne {


    public static void main(String[] args) {
        int testTimes = 1000000;
        int arr[] = new int[2];
        for (int i = 0; i < testTimes; i++) {
            arr[g()]++;
        }
        for (int i = 0; i < 2; i++) {
            System.out.println("arr[" + i + "] 出现的次数为 " + arr[i]);
        }
    }

    /**
     * 出现0的概率是0.84，出现1的概率是0.16
     * @return 不等概率返回0或1
     */
    public static int f() {
        return Math.random() < 0.84 ? 0 : 1;
    }

    /**
     * 生成一个01随机生成器
     * g()出现0，1的概率不同，可以用两位二进制位表示
     * 00、01、10、11
     * 即出现00和11的概率是不相等的，但出现01和10的概率是相等的，都是p(1-p)
     * 所以可用再次f()，如果再次不等就返回结果
     * @return 等概率返回0或1
     */
    public static int g() {
        int ans;
        do {
            ans = f();
        } while (ans == f());
        return ans;
    }
}
