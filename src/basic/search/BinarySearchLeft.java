package basic.search;

import basic.util.ArrayUtil;

import java.util.Arrays;

/**
 * 二分查找，给定一个数组arr和一个数字n，找出arr中数字>=n的最左边的位置
 */
public class BinarySearchLeft {

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = ArrayUtil.LenRandomValueRandom(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != searchLeft(arr, value)) {
                System.out.println("出错了！");
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking...");
    }


    public static int searchLeft(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] >= num) {
                index = mid;
                R = mid - 1;
            } else if (arr[mid] < num) {
                L = mid + 1;
            }
        }
        return index;
    }

    public static int test(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= num) {
                return i;
            }
        }
        return -1;
    }
}
