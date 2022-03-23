package basic.search;

import basic.util.ArrayUtil;

/**
 * 局部最小，只要找到一个最小的就返回下标
 * 规则：有一个无序数组，相邻的两个数不相等
 * 1.[0] < [1]，[0]是最小
 * 2.[N-2] > [N-1], [N-1]是最小
 * 3.[i-1]>[i]<[i+1], [i]是最小
 */
public class PartMin {

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxLen = 100;
        int maxValue = 200;
        for (int i = 0; i < testTime; i++) {
            int[] arr = ArrayUtil.randomArr(maxLen, maxValue);
            int ans = searchPartMin(arr);
            if (!check(arr, ans)) {
                ArrayUtil.print(arr);
                System.out.println(ans);
                break;
            }
        }
        System.out.println("测试结束");
    }

    /**
     * arr整体无序
     * arr相邻的两个数不相等
     * @param arr
     * @return
     */
    public static int searchPartMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int L = 1;
        int R = arr.length - 2;
        int index = -1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                index = mid;
                break;
            }
            if (arr[mid] > arr[mid - 1]) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    public static boolean check(int[] arr, int minIndex) {
        if (arr.length == 0) {
            return minIndex == -1;
        }
        int left = minIndex - 1;
        int right = minIndex + 1;
        boolean leftBigger = left < 0 || arr[left] > arr[minIndex];
        boolean rightBigger = right >= arr.length || arr[right] > arr[minIndex];
        return leftBigger && rightBigger;
    }
}
