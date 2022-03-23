package basic.search;

import basic.util.ArrayUtil;

import java.util.Arrays;

/**
 * 二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = ArrayUtil.LenRandomValueRandom(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != searchIndex(arr, value)) {
                System.out.println("出错了！");
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking...");
    }

    /**
     * 给定一个有序数组和要查找的数字，看是否存在
     * @param arr 有序数组
     * @param num 要查找的数字
     * @return 找到返回true，未找到返回false
     */
    public static boolean searchIndex(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            int mind = (L + R) / 2;
            if (arr[mind] == num) {
                return true;
            } else if (arr[mind] > num) {
                R = mind - 1;
            } else {
                L = mind + 1;
            }
        }
        return false;
    }

    public static boolean test(int[] arr, int num) {
        for (int cur : arr) {
            if (cur == num) {
                return true;
            }
        }
        return false;
    }


}
