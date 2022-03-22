package basic.sort;

import basic.util.ArrayUtil;

public class SortUtils {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void print(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public static int[] defaultArr() {
        int maxLen = 50;
        int maxValue = 1000;
        return ArrayUtil.LenRandomValueRandom(maxLen, maxValue);
    }
}
