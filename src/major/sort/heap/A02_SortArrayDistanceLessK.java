package major.sort.heap;

import tool.ArrayUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 题意：已知一个数组中的数，排完序后，原来位置上的每个数都移动了少于等于k个距离
 * 就是说数组排好序后，一个数从原来的位置i被换到排好序的位置j，j-i <= k
 */
public class A02_SortArrayDistanceLessK {

    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (k == 0) {
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        //从0~k-1 创建一个k大小的堆
        for (; index <= Math.min(arr.length - 1, k - 1); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; index++) {
            heap.add(arr[index]);
            arr[i++] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    public static void comparator(int[] arr, int k) {
        Arrays.sort(arr);
    }

    public static void main(String[] args) {
        int testTime = 10000000;
        int len = 100;
        int value = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * len) + 1;
            int[] arr = ArrayUtils.randomArrayNoMoveMoreK(len, value, k);
            int[] arr1 = ArrayUtils.copyArray(arr);
            int[] arr2 = ArrayUtils.copyArray(arr);
            sortedArrDistanceLessK(arr1, k);
            comparator(arr2, k);
            if (!ArrayUtils.isEqual(arr1, arr2)) {
                System.out.println("K : " + k);
                ArrayUtils.print(arr);
                ArrayUtils.print(arr1);
                ArrayUtils.print(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
