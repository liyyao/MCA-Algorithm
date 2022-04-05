package basic.sort;

import basic.util.ArrayUtil;
import org.w3c.dom.ranges.Range;

import java.util.Stack;

/**
 * 快速排序
 */
public class QuickSort {

    static class Info {
        int L;
        int R;

        public Info(int l, int r) {
            L = l;
            R = r;
        }
    }

    /**
     * 非递归
     * @param arr
     */
    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Stack<Info> stack = new Stack<>();
        stack.push(new Info(0, arr.length - 1));
        while (!stack.isEmpty()) {
            Info info = stack.pop();
            int[] equalE = partition(arr, info.L, info.R);
            if (equalE[0] > info.L) {
                stack.push(new Info(info.L, equalE[0] - 1));
            }
            if (equalE[1] < info.R) {
                stack.push(new Info(equalE[1] + 1, info.R));
            }
        }
    }

    /**
     * 递归
     * @param arr
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equalE = partition(arr, L, R);
        process(arr, L, equalE[0] - 1);
        process(arr, equalE[1] + 1, R);
    }

    public static int[] partition(int[] arr, int L, int R) {
        int lessR = L - 1;
        int moreR = R;
        int index = L;
        while (index < moreR) {
            if (arr[index] < arr[R]) {
                SortUtils.swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[R]) {
                SortUtils.swap(arr, index, --moreR);
            } else {
                index++;
            }
        }
        SortUtils.swap(arr, moreR, R);
        return new int[] {lessR + 1, moreR};
    }

    public static void splitNum(int[] arr) {
        int lessEqualR = -1;
        int index = 0;
        int len = arr.length;
        while (index < len) {
            if (arr[index] <= arr[len - 1]) {
                SortUtils.swap(arr, ++lessEqualR, index++);
            } else {
                index++;
            }
        }
    }

    public static void splitNum2(int[] arr) {
        int n = arr.length - 1;
        int lessEqualR = -1;
        int moreEqualR = n;
        int index = 0;
        while (index < moreEqualR) {
            if (arr[index] < arr[n]) {
                SortUtils.swap(arr, ++lessEqualR, index++);
            } else if (arr[index] == arr[n]) {
                index++;
            } else {
                SortUtils.swap(arr, index, --moreEqualR);
            }
        }
        SortUtils.swap(arr, moreEqualR, n);
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxLen = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ArrayUtil.LenRandomValueRandom(maxLen, maxValue);
            int[] arr2 = ArrayUtil.copyArray(arr1);
            quickSort2(arr1);
            MergeSort.mergeSort1(arr2);
            if (!ArrayUtil.isEqual(arr1, arr2)) {
                System.out.println("出错了");
                return;
            }
        }
        System.out.println("测试结束");
    }
}
