package basic.sort;

import basic.util.ArrayUtil;

/**
 * 冒泡排序
 * 在0 ~ N-1之间两两交换，将最大（最小）的放到N-1位置
 * 在0 ~ N-2之间两两交换，将最大（最小）的放到N-2位置
 * 在0 ~ N-3之间两两交换，将最大（最小）的放到N-3位置
 * ...
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = SortUtils.defaultArr();
        ArrayUtil.print(arr);
        bubbleSort(arr);
        ArrayUtil.print(arr);
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length -1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    SortUtils.swap(arr, j, j+1);
                }
            }
        }
    }
}
