package leetcode.merge;

import basic.util.ArrayUtil;

/**
 * https://leetcode.com/problems/count-of-range-sum/
 * https://leetcode-cn.com/problems/count-of-range-sum/
 * Given an integer array nums and two integers lower and upper, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.
 *
 * Constraints:
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * -10^5 <= lower <= upper <= 10^5
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 * Example1:
 * Input: nums = [-2,5,-1], lower = -2, upper = 2
 * Output: 3
 * Explanation: The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2.
 *
 * Example2:
 * Input: nums = [0], lower = 0, upper = 0
 * Output: 1
 */
public class T327_CountOfRangeSum {

    public static void main(String[] args) {
        //int[] arr = new int[]{-2,5,-1, 3, 2, -1};
        int[] arr = new int[]{-2147483647,0,-2147483647,2147483647};
        int lower = -564;
        int upper = 3864;
        T327_CountOfRangeSum obj = new T327_CountOfRangeSum();
        int ans = obj.prefixSum(arr, lower, upper);
        System.out.println(ans);
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null) {
            return 0;
        }
        return prefixSum(nums, lower, upper);
    }

    /**
     * 将数组转化为前缀和数组进行处理
     * @param arr
     * @param lower
     * @param upper
     * @return
     */
    private int prefixSum(int[] arr, int lower, int upper) {
        long[] sum = new long[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    //对前缀和进行归并排序
    //为什么可以排序而不影响结果，因为用的是前缀和，数组0 ~ R位置之前有多少个满足条件的数组区间，就是用R位置上的前缀和-R前面位置上的前缀和，
    // 落在给定的区间里就是符合条件的数
    private int process(long[] arr, int L, int R, int lower, int upper) {
        if (L == R) {
            if (arr[L] >= lower && arr[L] <= upper) {
                return 1;
            }
            return 0;
        }
        int M = L + ((R - L) >> 1);
        int leftCount = process(arr, L, M, lower, upper);
        int rightCount = process(arr, M + 1, R, lower, upper);
        int mergeCount = merge(arr, L, M, R, lower, upper);
        return leftCount + rightCount + mergeCount;
    }

    private int merge(long[] arr, int L, int M, int R, int lower, int upper) {
        int ans = 0;
        int windowL = L;
        int windowR = L;
        //这里窗口滑动的区间是[windowL, windowR)
        for (int k = M + 1; k <= R ; k++) {
            long min = arr[k] - upper;
            long max = arr[k] - lower;
            while (windowL <= M && arr[windowL] < min) {
                windowL++;
            }
            while (windowR <= M && arr[windowR] <= max) {
                windowR++;
            }
            ans += windowR - windowL;
        }

        long[] helper = new long[R - L + 1];
        int i = 0;
        int left = L;
        int right = M + 1;
        while (left <= M && right <= R) {
            helper[i++] = arr[left] <= arr[right] ? arr[left++] : arr[right++];
        }
        while (left <= M) {
            helper[i++] = arr[left++];
        }
        while (right <= R) {
            helper[i++] = arr[right++];
        }
        for (int j = 0; j < helper.length; j++) {
            arr[L + j] = helper[j];
        }
        return ans;
    }


    //----------------非窗口滑动版本-------------
    /**
     * 超时
     * @param arr
     * @param lower
     * @param upper
     * @return
     */
    private int prefixSum2(int[] arr, int lower, int upper) {
        long[] sum = new long[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            if (sum[i] >= lower && sum[i] <= upper) {
                ans += 1;
            }
            for (int j = i + 1; j < arr.length; j++) {
                long temp = sum[j] - sum[i];
                if (temp >= lower && temp <= upper) {
                    ans += 1;
                }
            }
        }
        return ans;
    }


    //======================下面的方法不对，当数据为-2147483647,0,-2147483647,2147483647时会产生数据溢出问题

    private int process2(int[] arr, int L, int R, int lower, int upper) {
        if (L == R) {
            /*int count = getCount(arr[L], lower, upper);
            if (count == 1) {
                System.out.print("[" + L + "," + L + "], ");
            }*/
            return getCount(arr[L], lower, upper);
        }
        int M = L + ((R - L) >> 1);
        int ans1 = process2(arr, L, M, lower, upper);
        int ans2 = process2(arr, M + 1, R, lower, upper);
        int ans = merge2(arr, L, M, R, lower, upper);
        return ans1 + ans2 + ans;
    }

    private int merge2(int[] arr, int L, int M, int R, int lower, int upper) {
        int leftCount = 0;
        for (int i = L; i <= M; i++) {
            leftCount += arr[i];
        }
        int ans = 0;
        for (int i = L; i <= M; i++) {
            int rightCount = 0;
            for (int j = M + 1; j <= R; j++) {
                rightCount += arr[j];
                /*int count = getCount(leftCount + rightCount, lower, upper);
                if (count == 1) {
                    System.out.print("[" + i + "," + j + "], ");
                }*/
                ans += getCount(leftCount + rightCount, lower, upper);
            }
            leftCount -= arr[i];
        }
        return ans;
    }

    private int getCount(long value, int lower, int upper) {
        return (value >= lower && value <= upper) ? 1 : 0;
    }

    /**
     * 超时
     * @param arr
     * @param lower
     * @param upper
     * @return
     */
    public int test(int[] arr, int lower, int upper) {
        if (arr == null) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans += getCount(arr[i], lower, upper);
            long count = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                count += arr[j];
                ans += getCount(count, lower, upper);
            }
        }
        return ans;
    }
}
