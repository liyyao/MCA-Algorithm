package basic.sort;

import basic.util.ArrayUtil;

/**
 * 归并排序
 */
public class MergeSort {

    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length -1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int step = 1;
        int len = arr.length;
        while (step < len) {
            int l = 0;
            while (l < len) {
                int mid;
                if (len - l >= step) {
                    mid = l + step - 1;
                } else {
                    mid = len - 1;
                }
                if (mid == len - 1) {
                    break;
                }
                int r;
                if (len - 1 - mid >= step) {
                    r = mid + step;
                } else {
                    r = len - 1;
                }
                merge(arr, l, mid, r);
                if (r == len - 1) {
                    break;
                } else {
                    l = r + 1;
                }
            }
            if (step > len / 2) {
                break;
            }
            step *= 2;
        }
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] temp = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            temp[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            temp[i++] = arr[p1++];
        }
        while (p2 <= r) {
            temp[i++] = arr[p2++];
        }
        for (i = 0; i < temp.length; i++) {
            arr[l + i] = temp[i];
        }
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    private static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxLen = 100;
        int maxValue = 1000;
        System.out.println("开始测试");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = ArrayUtil.LenRandomValueRandom(maxLen, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!ArrayUtil.isEqual(arr1, arr2)) {
                System.out.println("出错了");
                printArray(arr1);
                printArray(arr2);
            }
        }
        System.out.println("测试结束");
    }


}
