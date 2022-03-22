package basic.random;

/**
 * Java中的Math.random()函数
 * Math.random()函数返回的是[0,1)上的随机数，且是等概率的
 */
public class RandomDemo {

    public static void main(String[] args) {
        checkRandomEquals();
        //x2xPow2();
    }

    /**
     * 使Math.random落在0~X上的概率为0~X的平方
     */
    public static void x2xPow2() {
        double value = 0.7;
        int count = 0;
        int rang = 100000;
        for (int i = 0 ; i < rang; i++) {
            if (Math.max(Math.random(), Math.random()) < value) {
                count++;
            }
        }
        System.out.println("随机数小于" + value + "的概率 " + ((double) count / (double) rang));
        System.out.println("随机数的平方是 " + Math.pow(value, 2));
    }

    /**
     * 验证Math.random落在0~X上的概率为X
     */
    public static void checkRandom() {
        double value = 0.9;
        int count = 0;
        int rang = 100000;
        for (int i = 0 ; i < rang; i++) {
            if (Math.random() < value) {
                count++;
            }
        }
        System.out.println("随机数小于" + value + "的概率 " + ((double) count / (double) rang));
    }

    /**
     * 验证Math.random落在0~8上的概率为X
     */
    public static void checkRandomEquals() {
        int k = 9;
        int arr[] = new int[k];
        int testTimes = 10000000;
        for (int i = 0 ; i < testTimes; i++) {
            int v = (int) (Math.random() * k); //[0,k-1]
            arr[v]++;
        }
        for (int i = 0; i < k; i++) {
            System.out.println(i + "这个数出现了 " + arr[i] + " 次");
        }
    }
}
