package major.sort.merge;

import basic.util.ArrayUtil;

/**
 * 求一个数组中的逆序对
 * 例：3 1 0 4 3 1
 * 逆序对有：(3 1),(3 0),(3 1),(1 0),(4 3),(4 1),(3 1)
 */
public class A03_BiggerThanRight {

    public static void main(String[] args) {
        int testTime = 1000000;
        int len = 100;
        int value = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ArrayUtil.LenRandomValueRandom(len, value);
            int[] arr2 = ArrayUtil.copyArray(arr1);
            int ans1 = biggerThanRight2(arr1);
            int ans2 = test(arr2);
            if (ans1 != ans2) {
                System.out.println("出错了");
                return;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * 递归
     * @param arr
     * @return
     */
    public static int biggerThanRight(int[] arr) {
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

    public static int biggerThanRight2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int step = 1;
        int N = arr.length;
        int ans = 0;
        while (step < N) {
            int L = 0;
            while (L < N) {
                int M = (L + step - 1);
                if (M >= N) {
                    break;
                }
                int R = Math.min(M + step, N - 1);
                ans += merge(arr, L, M, R);
                L = R + 1;
            }
            if (step > N / 2) {
                break;
            }
            step <<= 1;
        }
        return ans;
    }

    private static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int index = 0;
        int left = L;
        int right = M + 1;
        int ans = 0;
        while (left <= M && right <= R) {
            if (arr[left] > arr[right]) {
                ans += (M - left + 1);
                /*int temp = left;
                while (temp <= M) {
                    System.out.print("(" + arr[temp++] + "," + arr[right] + ") ");
                }*/
                help[index++] = arr[right++];
            } else {
                help[index++] = arr[left++];
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
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
