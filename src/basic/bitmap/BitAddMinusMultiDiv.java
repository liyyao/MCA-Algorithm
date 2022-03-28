package basic.bitmap;

/**
 * 使用位运算实现加法
 */
public class BitAddMinusMultiDiv {

    /**
     * 位运算实现加法
     * 原理就是
     * a ^ b 实现的是a的二进制和b的二进制无进位相加
     * a & b << 1 实现的是a + b的进位
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;    //无进位相加
            b = (a & b) << 1;   //进位信息
            a = sum;
        }
        return sum;
    }

    /**
     * 获取相反数
     * 相反数就是将原数据取反+1
     * @param n
     * @return n的相反数
     */
    public static int negNum(int n) {
        return add(~n, 1);
    }

    /**
     * 减法，转换成加法计算
     * a - b = a + (-b)
     * @param a
     * @param b
     * @return
     */
    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    /**
     * 乘法
     * @param a
     * @param b
     * @return
     */
    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    public static boolean isNeg(int n) {
        return n < 0;
    }

    /**
     * 除法就是利用乘法的加
     * 1.用被除数 - 离自己最近的一个2的n次方 * b，
     * 2.用第1步得到的数做为被除数 再执行步骤1
     * 3.直到1最后的结果找不到离自己最近的2的n次方 * b
     * 得到的数就是最后的结果
     * @param a
     * @param b
     * @return
     */
    public static int div(int a, int b) {
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 30; i >= 0 ; i = minus(i, 1)) {
            if ((x >> i) >= y) {
                res |= (1 << i);
                x = minus(x, y << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if (b == negNum(1)) {
                return Integer.MAX_VALUE;
            } else {
                // 如果是系统最小值，没办法取绝对值，所以先加一个1，除以除数，
                // 然后再用最小值-除数乘以商的值，用得到的结果再除以除数，得到一个补偿值
                int ans = div(add(a, 1), b);
                return add(ans, div(minus(a, multi(ans, b)), b));
            }
        } else {
            return div(a, b);
        }
    }
}
