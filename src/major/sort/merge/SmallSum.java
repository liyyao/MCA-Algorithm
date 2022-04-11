package major.sort.merge;

import tool.ArrayUtil;

/**
 * 求最小和，给定一个数组，每一个数前面比他小的数相加，最终将这些和相加
 * 例：4  6  8  3  5  2  6  2  1
 *    0  4  10 0  7  0  14 0  0 = 35
 *    4前面比它小的数没有 所以是0
 *    6前面比它小的数是4 所以是4
 *    8前面比它小的数是4和6 所以是10
 *    ...
 */
public class SmallSum {

    public static void main(String[] args) {

        int testTime = 1000000;
        int len = 100;
        int value = 100;

        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = ArrayUtil.LenRandomValueRandom(len, value);
            int test = test(arr);
            int ans = smallSum(arr);
            if (test != ans) {
                System.out.println("出错了");
                return;
            }
        }
        System.out.println("测试结束");
    }

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);
        int ans1 = process(arr, L, M);
        int ans2 = process(arr, M + 1, R);
        int ans = merge(arr, L, M, R);
        return ans1 + ans2 + ans;
    }

    private static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int ans = 0;
        int index = 0;
        int left = L;
        int right = M + 1;
        while (left <= M && right <= R) {
            if (arr[left] < arr[right]) {
                ans += (R - right + 1) * arr[left];
                help[index++] = arr[left++];
            } else {
                help[index++] = arr[right++];
            }
        }
        while (left <= M) {
            help[index++] = arr[left++];
        }
        while (right <= R) {
            help[index++] = arr[right++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    public static int test(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    ans += arr[j];
                }
            }
        }
        return ans;
    }
}
