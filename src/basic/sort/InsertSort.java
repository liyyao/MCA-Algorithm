package basic.sort;

import basic.util.ArrayUtil;

/**
 * 插入排序
 * 保证当前位置前面的数都已排好序，就像打扑克一样插入排序
 * 0的位置只有一个数，已经排好序了
 * 0 ~ 1的位置，比较1位置上的数字，比0位置上的数小，就交换
 * 0 ~ 2的位置，比较2位置睥数，比1位置上的数大就不用交换，比1位置上的数小就和1位置上的数交换，然后再比较1位置上的数和0位置上的数
 * ...
 * 在0 ~ M区间的数都是有序的，然后用M+1的位置上的数和M位置比，小的话交换并继续与M-1位置上的数比，小的话继续交换比较，大的话就不用交换，以此类推
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = SortUtils.defaultArr();
        ArrayUtil.print(arr);
        //insertSort(arr);
        insertSort2(arr);
        ArrayUtil.print(arr);
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                SortUtils.swap(arr, j, j-1);
                j--;
            }
        }
    }

    public static void insertSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j + 1] < arr[j]; j--) {
                SortUtils.swap(arr, j, j + 1);
            }
        }
    }
}
