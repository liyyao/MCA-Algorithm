package major.sort.merge;

import basic.util.ArrayUtil;

/**
 * 求一个数组中的当前数右边的数的2倍比当前数小的个数
 * 例：3 1 0 4 3 1
 * 3: 右边的2倍比3小的有 1 0 1
 * 1：右边的2倍比1小的有 0
 * 0：右边的2倍比0小的没有
 * 4：右边的2倍比4小的有 1
 * 3：右边的2倍比3小的有 1
 * 1：没有了
 */
public class A04_BiggerThanRightTwice {

    public static void main(String[] args) {
        bigTest();
        //smallTest();
    }

    private static void smallTest() {
        int[] arr = new int[] {3,1,0,4,3,1};
        int ans = biggerThanRightTwice(arr);
        System.out.println(ans);
    }

    private static void bigTest() {
        int testTime = 1000000;
        int len = 100;
        int value = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ArrayUtil.LenRandomValueRandom(len, value);
            int[] arr2 = ArrayUtil.copyArray(arr1);
            int ans1 = biggerThanRightTwice2(arr1);
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
    public static int biggerThanRightTwice(int[] arr) {
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

    public static int biggerThanRightTwice2(int[] arr) {
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
                ans += merge2(arr, L, M, R);
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
                int temp = left;
                while (temp <= M) {
                    if ((arr[temp] - 1) / 2 + 1 > arr[right]) {
                        break;
                    }
                    temp++;
                }
                if (temp <= M) {
                    ans += (M - temp + 1);
                }
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

    /**
     * 这里不回退算法，且将上面一个wile里面套while改成一个for和一个while
     * 逻辑上更好理解
     * 这里用到了单调性，所以可以不回退
     * @param arr
     * @param L
     * @param M
     * @param R
     * @return
     */
    private static int merge2(int[] arr, int L, int M, int R) {
        int ans = 0;
        int windowR = M + 1;
        for (int i = L; i <= M; i++) {
            while (windowR <= R && arr[i] > (arr[windowR] * 2)) {
                windowR++;
            }
            ans += windowR - M - 1;
        }

        int[] help = new int[R - L + 1];
        int index = 0;
        int left = L;
        int right = M + 1;
        while (left <= M && right <= R) {
            help[index++] = arr[left] <= arr[right] ? arr[left++] : arr[right++];
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
                if (arr[i] > arr[j] * 2) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
