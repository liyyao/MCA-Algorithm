package basic.sort;

import basic.util.ArrayUtil;

/**
 * 选择排序
 * 某个区间查出最小的排放在前面
 * 0 ~ N-1 区间找到最小的放到0位置
 * 1 ~ N-1 区间找到最小的放到1位置
 * 2 ~ N-1 区间找到最小的放到2位置
 * ...
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] a = SortUtils.defaultArr();
        ArrayUtil.print(a);
        selectSort(a);
        ArrayUtil.print(a);
    }

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            SortUtils.swap(arr, i, minValueIndex);
        }
    }

}
